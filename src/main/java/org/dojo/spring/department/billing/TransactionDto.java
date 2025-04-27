package org.dojo.spring.department.billing;

import java.math.BigDecimal;

public record TransactionDto(BigDecimal amount, String departmentName) { }
