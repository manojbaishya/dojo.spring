package org.dojo.spring.department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByName(String name);
    Optional<Department> findByNameIgnoreCase(String name);
    
    @Query("SELECT d FROM Department d")
    List<DepartmentProjection> findAllWithoutTransactions();
}
