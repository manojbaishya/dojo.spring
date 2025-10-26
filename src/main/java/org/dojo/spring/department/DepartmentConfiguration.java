package org.dojo.spring.department;

import org.dojo.spring.department.billing.TransactionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class DepartmentConfiguration {
    @Bean
    @RequestScope
    DepartmentService departmentService(DepartmentRepository departmentRepository, TransactionRepository transactionRepository) {
        return new StandardDepartmentService(departmentRepository, transactionRepository);
    }
}
