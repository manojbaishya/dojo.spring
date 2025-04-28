package org.dojo.spring.department;

import org.dojo.spring.department.billing.TransactionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DepartmentConfiguration {
    @Bean
    public DepartmentService departmentService(DepartmentRepository departmentRepository, TransactionRepository transactionRepository) {
        return new StandardDepartmentService(departmentRepository, transactionRepository);
    }
}
