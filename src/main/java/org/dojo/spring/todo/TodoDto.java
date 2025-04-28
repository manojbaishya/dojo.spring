package org.dojo.spring.todo;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public record TodoDto(@Nullable String id, @NotBlank String title, Boolean isCompleted) { }
