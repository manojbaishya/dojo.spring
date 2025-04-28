package org.dojo.spring.todo;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TodoMapper {
    TodoMapper INSTANCE = Mappers.getMapper(TodoMapper.class);

    Todo deserialize(TodoDto dto);

    TodoDto serialize(Todo entity);
}
