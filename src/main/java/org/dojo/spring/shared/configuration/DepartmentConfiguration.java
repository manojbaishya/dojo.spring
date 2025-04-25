package org.dojo.spring.shared.configuration;

import org.dojo.spring.department.DepartmentRepository;
import org.dojo.spring.department.DepartmentService;
import org.dojo.spring.department.StandardDepartmentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DepartmentConfiguration {
    @Bean
    public DepartmentService departmentService(DepartmentRepository departmentRepository) { return new StandardDepartmentService(departmentRepository); }
}
