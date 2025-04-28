package org.dojo.spring.todo;

import org.dojo.spring.shared.exceptions.ResourceNotFoundException;

import java.util.List;

public interface TodoService {
    List<Todo> getAllTodos();

    Todo getTodoById(Long id) throws ResourceNotFoundException;

    Todo getTodoByTitle(String title) throws ResourceNotFoundException;

    Todo addTodo(Todo todo);

    Todo updateTodo(Long id, Todo todo) throws ResourceNotFoundException;

    Boolean deleteTodoById(Long id) throws ResourceNotFoundException;
}
