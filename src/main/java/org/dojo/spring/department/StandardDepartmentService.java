package org.dojo.spring.department;

import org.dojo.spring.shared.exceptions.ResourceNotFoundException;
import org.dojo.spring.shared.utilities.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.dojo.spring.shared.utilities.StringUtils.isNotNullOrEmpty;

@Service
public class StandardDepartmentService implements DepartmentService {
    private static final String ERROR_MESSAGE = "Department does not exist for this id: %s";

    private final DepartmentRepository departmentRepository;
    public StandardDepartmentService(DepartmentRepository departmentRepository) { this.departmentRepository = departmentRepository; }

    @Override
    public Department addDepartment(final Department department) { return departmentRepository.save(department); }

    @Override
    public List<Department> getAllDepartments() { return departmentRepository.findAll(); }

    @Override
    public Department getDepartmentById(final Long departmentId) throws ResourceNotFoundException {
        return departmentRepository.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException(ERROR_MESSAGE.formatted(departmentId)));
    }

    @Override
    public Department getDepartmentByName(final String departmentName) throws ResourceNotFoundException {
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName).orElseThrow(() -> new ResourceNotFoundException(String.format("Department with name %s not found!", departmentName)));
    }

    @Override
    public Department updateDepartment(final Long departmentId, final Department department) throws ResourceNotFoundException {
        final Department currentDepartment =
                departmentRepository.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException(ERROR_MESSAGE.formatted(departmentId)));

        if (isNotNullOrEmpty(department.getDepartmentName()))
            currentDepartment.setDepartmentName(department.getDepartmentName());
        if (isNotNullOrEmpty(department.getDepartmentAddress()))
            currentDepartment.setDepartmentAddress(department.getDepartmentAddress());
        if (isNotNullOrEmpty(department.getDepartmentCode()))
            currentDepartment.setDepartmentCode(department.getDepartmentCode());

        return departmentRepository.save(currentDepartment);
    }

    @Override
    public boolean deleteDepartmentById(final Long departmentId) throws ResourceNotFoundException {
        final Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException(ERROR_MESSAGE.formatted(departmentId)));
        try {
            departmentRepository.delete(department);
            return true;
        } catch (final Exception ex) {
            return false;
        }
    }
}
