package org.dojo.spring.department;

import org.dojo.spring.shared.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StandardDepartmentServiceTests {
    @Autowired
    private DepartmentService departmentService;

    @MockitoBean
    private DepartmentRepository departmentRepository;

    private final Department expected = Department
            .builder(96378541L)
            .name("SampleDepartmentName")
            .address("SampleDepartmentAddress")
            .code("XC32B")
            .build();
    @BeforeEach
    void setup() {
        Mockito.when(departmentRepository.findByNameIgnoreCase(expected.getName())).thenReturn(Optional.of(expected));
    }

    @Test
    @DisplayName("Get department by name.")
    void whenValidDepartmentNameIsGivenThenDepartmentShouldBeFound() throws ResourceNotFoundException {
        Department actual = departmentService.getDepartmentByName(expected.getName());
        assertThat(actual).isNotNull();
        assertThat(actual.getName()).isEqualTo(expected.getName());
    }
}
