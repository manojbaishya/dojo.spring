package org.dojo.spring.todo;

import jakarta.validation.Valid;
import org.dojo.spring.shared.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoController {
    private final Logger logger = LoggerFactory.getLogger(TodoController.class);

    private final TodoService todoService;
    public TodoController(TodoService todoService) { this.todoService = todoService; }

    @PostMapping(value = "/todo")
    public ResponseEntity<TodoDto> addTodo(@RequestBody @Valid TodoDto todoDto) {
        logger.info("Creating Todo object.");
        var todo = TodoMapper.INSTANCE.deserialize(todoDto);
        return ResponseEntity.ok(TodoMapper.INSTANCE.serialize(todoService.addTodo(todo)));
    }

    @GetMapping(value = "/todos")
    public ResponseEntity<List<TodoDto>> getTodos() {
        logger.info("Getting all Todo objects.");
        return ResponseEntity.ok(todoService.getAllTodos().stream().map(TodoMapper.INSTANCE::serialize).toList());
    }

    @GetMapping(value = "/todo", params = {"id"})
    public ResponseEntity<TodoDto> getTodoById(@RequestParam(name = "id") Long todoId) throws ResourceNotFoundException {
        logger.info("Getting Todo object by id.");
        return ResponseEntity.ok(TodoMapper.INSTANCE.serialize(todoService.getTodoById(todoId)));
    }

    @GetMapping(value = "/todo", params = {"title"})
    public ResponseEntity<TodoDto> getTodoByTitle(@RequestParam(name = "title") String todoTitle) throws ResourceNotFoundException {
        logger.info("Getting Todo object by title.");
        return ResponseEntity.ok(TodoMapper.INSTANCE.serialize(todoService.getTodoByTitle(todoTitle)));
    }

    @PutMapping(value = "/todo/{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable("id") Long todoId, @RequestBody @Valid TodoDto todoDto) throws ResourceNotFoundException {
        logger.info("Updating Todo object.");
        var todo = TodoMapper.INSTANCE.deserialize(todoDto);
        return ResponseEntity.ok(TodoMapper.INSTANCE.serialize(todoService.updateTodo(todoId, todo)));
    }

    @DeleteMapping(value = "/todo/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId) throws ResourceNotFoundException {
        logger.info("Deleting Todo object.");
        if (todoService.deleteTodoById(todoId))
            return ResponseEntity.ok().body(String.format("Todo Id #%d deleted!", todoId));
        else throw new ResourceNotFoundException(String.format("Todo Id #%d is not deleted!", todoId));
    }
}
