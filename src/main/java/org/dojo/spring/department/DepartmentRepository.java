package org.dojo.spring.department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    public Optional<Department> findByName(String name);
    public Optional<Department> findByNameIgnoreCase(String name);
}
