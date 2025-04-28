package org.dojo.spring.todo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TodoDependencies {
    @Bean
    public TodoService todoService(TodoRepository todoRepository) {
        return new StandardTodoService(todoRepository);
    }

}
