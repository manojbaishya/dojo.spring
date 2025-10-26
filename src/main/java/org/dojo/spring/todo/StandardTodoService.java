package org.dojo.spring.todo;

import org.dojo.spring.shared.exceptions.ResourceNotFoundException;

import java.util.List;

import static org.dojo.spring.shared.utilities.StringUtils.isNotNullOrEmpty;

public class StandardTodoService implements TodoService {
    private static final String ERROR_MESSAGE = "Todo does not exist for this id: %s";

    private final TodoRepository todoRepository;
    public StandardTodoService(TodoRepository todoRepository) { this.todoRepository = todoRepository; }

    @Override
    public List<Todo> getAllTodos() { return todoRepository.findAll(); }

    @Override
    public Todo getTodoById(Long id) throws ResourceNotFoundException { return todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ERROR_MESSAGE.formatted(id))); }


    @Override
    public Todo getTodoByTitle(String title) throws ResourceNotFoundException {
        return todoRepository.findByTitle(title).orElseThrow(() -> new ResourceNotFoundException(ERROR_MESSAGE.formatted(title)));
    }

    @Override
    public Todo addTodo(Todo todo) { return todoRepository.save(todo); }

    @Override
    public Todo updateTodo(Long id, Todo todo) throws ResourceNotFoundException {

        Todo currentTodo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ERROR_MESSAGE.formatted(id)));

        if (isNotNullOrEmpty(todo.getTitle())) currentTodo.setTitle(todo.getTitle());
        currentTodo.setIsCompleted(todo.getIsCompleted());

        return todoRepository.save(currentTodo);
    }

    @Override
    public Boolean deleteTodoById(Long id) throws ResourceNotFoundException {
        final Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ERROR_MESSAGE.formatted(id)));

        try {
            todoRepository.delete(todo);
            return true;
        } catch (final Exception ex) {
            return false;
        }
    }
}
