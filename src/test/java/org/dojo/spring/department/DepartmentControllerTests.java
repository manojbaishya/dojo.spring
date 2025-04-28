package org.dojo.spring.department;

import org.dojo.spring.shared.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(DepartmentController.class)
class DepartmentControllerTests {
    private final WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    public DepartmentControllerTests(WebApplicationContext applicationContext) { this.webApplicationContext = applicationContext; }

    private final Department department = Department.builder(98765L)
                                                    .name("TestControllerDepartment")
                                                    .address("TestControllerDepartmentAddress")
                                                    .code("TCD001")
                                                    .build();

    @MockitoBean
    private DepartmentService departmentService;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testAddDepartment() throws Exception {
        var inputDepartment = Department.builder(null)
                                        .name(department.getName())
                                        .address(department.getAddress())
                                        .code(department.getCode())
                                        .build();

        Mockito.when(departmentService.addDepartment(inputDepartment)).thenReturn(department);

        mockMvc.perform(post("/api/department")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format(
                                """
                                                    {
                                                        "name": "%s",
                                                        "address": "%s",
                                                        "code": "%s"
                                                    }
                                        """,
                                department.getName(),
                                department.getAddress(),
                                department.getCode()
                        )
                )
        ).andExpect(status().isOk());
    }

    @Test
    void testGetDepartmentById() throws ResourceNotFoundException {
        long id = 98765L;
        Mockito.when(departmentService.getDepartmentById(id)).thenReturn(department);

        try {
            mockMvc.perform(get("/api/department?id={departmentId}", id)
                           .contentType(MediaType.APPLICATION_JSON)
                   )
                   .andExpect(status().isOk())
                   .andExpect(jsonPath("$.name").value(department.getName())
                   );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
