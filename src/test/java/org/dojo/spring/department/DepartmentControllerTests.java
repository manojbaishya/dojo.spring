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
                                                    .departmentName("TestControllerDepartment")
                                                    .departmentAddress("TestControllerDepartmentAddress")
                                                    .departmentCode("TCD001")
                                                    .build();

    @MockitoBean
    private DepartmentService departmentService;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testSaveDepartment() throws Exception {
        var inputDepartment = Department.builder(null)
                                        .departmentName(department.getDepartmentName())
                                        .departmentAddress(department.getDepartmentAddress())
                                        .departmentCode(department.getDepartmentCode())
                                        .build();

        Mockito.when(departmentService.addDepartment(inputDepartment)).thenReturn(department);

        mockMvc.perform(post("/department")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(String.format(
                                       """
                                                           {
                                                               "departmentName": "%s",
                                                               "departmentAddress": "%s",
                                                               "departmentCode": "%s"
                                                           }
                                               """,
                                       department.getDepartmentName(),
                                       department.getDepartmentAddress(),
                                       department.getDepartmentCode()
                               )
                       )
               )
               .andExpect(status().isOk());
    }

    @Test
    void testGetDepartmentById() throws ResourceNotFoundException {
        long id = 98765L;
        Mockito.when(departmentService.getDepartmentById(id)).thenReturn(department);

        try {
            mockMvc.perform(get("/department?id={departmentId}", id)
                           .contentType(MediaType.APPLICATION_JSON)
                   )
                   .andExpect(status().isOk())
                   .andExpect(jsonPath("$.departmentName").value(department.getDepartmentName())
                   );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
