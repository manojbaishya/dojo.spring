package org.dojo.spring.department;

import org.dojo.spring.department.billing.TransactionMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = TransactionMapper.class)
public interface DepartmentMapper {
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    @Mapping(target = "id", ignore = true)
    Department deserialize(DepartmentDto dto);

    @InheritInverseConfiguration
    DepartmentDto serialize(Department entity);
}
