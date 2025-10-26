package org.dojo.spring.department;

import static org.dojo.spring.shared.utilities.StringUtils.isNotNullOrEmpty;

import java.util.List;

import org.dojo.spring.department.billing.Transaction;
import org.dojo.spring.department.billing.TransactionRepository;
import org.dojo.spring.shared.exceptions.ResourceNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

//@Service // unused because programmatically registered in DepartmentConfiguration.java
public class StandardDepartmentService implements DepartmentService {
    private static final String ERROR_MESSAGE = "Department does not exist for this id: %s";

    private final DepartmentRepository departmentRepository;
    private final TransactionRepository transactionRepository;

    public StandardDepartmentService(DepartmentRepository departmentRepository,
            TransactionRepository transactionRepository) {
        this.departmentRepository = departmentRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Department addDepartment(final Department department) { return departmentRepository.save(department); }

    @Override
    public List<DepartmentProjection> getAllDepartments() { return departmentRepository.findAllWithoutTransactions(); }

    @Override
    public List<Department> getAllDepartmentsWithTransactions() { return departmentRepository.findAll(); }

    @Override
    @Cacheable(value = "departments", key = "#departmentId")
    public Department getDepartmentById(final Long departmentId) throws ResourceNotFoundException {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException(ERROR_MESSAGE.formatted(departmentId)));
    }

    @Override
    // @Cacheable(value = "departments", key = "#departmentName")
    public Department getDepartmentByName(final String departmentName) throws ResourceNotFoundException {
        return departmentRepository.findByNameIgnoreCase(departmentName)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Department with name %s not found!", departmentName)));
    }

    @Override
    @Cacheable(value = "departments", key = "#departmentName")
    public DepartmentProjectionDto getDepartmentByNameWithoutTransactions(String departmentName)
            throws ResourceNotFoundException {
        DepartmentProjection department = departmentRepository.findByNameIgnoreCaseWithoutTransactions(departmentName);
        if (department == null)
            throw new ResourceNotFoundException(String.format("Department with name %s not found!", departmentName));
        return new DepartmentProjectionDto(department.getId(), department.getName(), department.getCode(),
                department.getAddress());
    }

    @Override
    @CachePut(value = "departments", key = "#department.id")
    public Department updateDepartment(final Long departmentId, final Department department)
            throws ResourceNotFoundException {
        final Department currentDepartment = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException(ERROR_MESSAGE.formatted(departmentId)));

        if (isNotNullOrEmpty(department.getName())) currentDepartment.setName(department.getName());
        if (isNotNullOrEmpty(department.getAddress())) currentDepartment.setAddress(department.getAddress());
        if (isNotNullOrEmpty(department.getCode())) currentDepartment.setCode(department.getCode());

        return departmentRepository.save(currentDepartment);
    }

    @Override
    @CacheEvict(value = "departments", key = "#departmentId")
    public boolean deleteDepartmentById(final Long departmentId) throws ResourceNotFoundException {
        final Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException(ERROR_MESSAGE.formatted(departmentId)));
        try {
            departmentRepository.delete(department);
            return true;
        } catch (final Exception ex) {
            return false;
        }
    }

    @Override
    public Transaction addTransaction(Department department, Transaction transaction) {
        transaction.setDepartment(department);
        return transactionRepository.save(transaction);
    }
}
