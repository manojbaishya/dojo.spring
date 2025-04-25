package org.dojo.spring.department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    public Optional<Department> findByDepartmentName(String departmentName);
    public Optional<Department> findByDepartmentNameIgnoreCase(String departmentName);
}
