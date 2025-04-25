package org.dojo.spring.department;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    private final Department expected = Department.builder(null)
                                                  .departmentName("Mechanical Engineering")
                                                  .departmentAddress("Tower C Building")
                                                  .departmentCode("ME001")
                                                  .build();

    @BeforeEach
    void setup() {
        var department = Department.builder(null)
                                   .departmentName(expected.getDepartmentName())
                                   .departmentAddress(expected.getDepartmentAddress())
                                   .departmentCode(expected.getDepartmentCode())
                                   .build();

        testEntityManager.persist(department);
    }

    @Test
    void whenFindByIdThenReturnDepartment() {
        Optional<Department> department = departmentRepository.findByDepartmentName(expected.getDepartmentName());
        department.ifPresent(dept -> assertThat(dept.getDepartmentCode()).isEqualTo(expected.getDepartmentCode()));
    }
}
