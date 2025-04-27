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
                                                  .name("Mechanical Engineering")
                                                  .address("Tower C Building")
                                                  .code("ME001")
                                                  .build();

    @BeforeEach
    void setup() {
        var department = Department.builder(null)
                                   .name(expected.getName())
                                   .address(expected.getAddress())
                                   .code(expected.getCode())
                                   .build();

        testEntityManager.persist(department);
    }

    @Test
    void whenFindByIdThenReturnDepartment() {
        Optional<Department> department = departmentRepository.findByName(expected.getName());
        department.ifPresent(dept -> assertThat(dept.getCode()).isEqualTo(expected.getCode()));
    }
}
