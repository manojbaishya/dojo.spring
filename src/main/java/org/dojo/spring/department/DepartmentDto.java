package org.dojo.spring.department;

import org.dojo.spring.department.billing.TransactionDto;

import java.util.List;

public record DepartmentDto(String name, String address, String code, List<TransactionDto> transactions) { }
