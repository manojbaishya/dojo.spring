package org.dojo.spring.department.billing;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "departmentName", target = "department.name")
    Transaction deserialize(TransactionDto dto);

    @Mapping(source = "department.name", target = "departmentName")
    TransactionDto serialize(Transaction txn);
}
