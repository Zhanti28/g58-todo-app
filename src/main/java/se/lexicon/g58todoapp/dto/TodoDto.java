package se.lexicon.g58todoapp.dto;

import lombok.Builder;
import se.lexicon.g58todoapp.entity.Todo;

import java.time.LocalDateTime;

@Builder
public record TodoDto(
        Long id,
        String title,
        String description,
        LocalDateTime dueDate,
        boolean completed,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Long assignedToId,
        int numberOfAttachments
) {

    public static TodoDto convertToDto(Todo todo) {
        return TodoDto.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .dueDate(todo.getDueDate())
                .completed(todo.getCompleted())
                .createdAt(todo.getCreatedAt())
                .updatedAt(todo.getUpdatedAt())
                .assignedToId(todo.getAssignedTo() != null ? todo.getAssignedTo().getId() : null)
                .numberOfAttachments(todo.getAttachments().size())
                .build();
    }
}
