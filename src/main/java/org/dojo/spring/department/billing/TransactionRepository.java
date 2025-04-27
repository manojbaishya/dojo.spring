package org.dojo.spring.department.billing;

import org.dojo.spring.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByDepartment(Department department);

    List<Transaction> findByDepartmentId(Long departmentId);
    
    List<Transaction> findByDepartmentName(String departmentName);
}
