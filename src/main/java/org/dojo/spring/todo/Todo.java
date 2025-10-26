package org.dojo.spring.todo;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "todos")
public class Todo {
    public Todo() { }
    public Todo(Long id, String title, Boolean isCompleted) {
        this.id = id;
        this.title = title;
        this.isCompleted = isCompleted;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    @Column(length = 50)
    @Size(max = 50)
    @NotBlank(message = "Title cannot be empty.")
    private String title;
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    private Boolean isCompleted;
    public Boolean getIsCompleted() { return isCompleted; }
    public void setIsCompleted(Boolean isCompleted) { this.isCompleted = isCompleted; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Todo todo)) return false;
        return Objects.equals(getId(), todo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
