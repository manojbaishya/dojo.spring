package org.dojo.spring.department;

import org.dojo.spring.department.billing.Transaction;
import org.dojo.spring.shared.exceptions.ResourceNotFoundException;

import java.util.List;

public interface DepartmentService {
    List<DepartmentProjection> getAllDepartments();

    List<Department> getAllDepartmentsWithTransactions();

    Department getDepartmentById(Long departmentId) throws ResourceNotFoundException;

    Department getDepartmentByName(String departmentName) throws ResourceNotFoundException;

    DepartmentProjectionDto getDepartmentByNameWithoutTransactions(String departmentName) throws ResourceNotFoundException;

    Department addDepartment(Department department);

    Department updateDepartment(Long departmentId, Department department) throws ResourceNotFoundException;

    boolean deleteDepartmentById(Long departmentId) throws ResourceNotFoundException;

    Transaction addTransaction(Department department, Transaction transaction);
}
