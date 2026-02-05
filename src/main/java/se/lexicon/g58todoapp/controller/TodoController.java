package se.lexicon.g58todoapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.g58todoapp.dto.TodoDto;
import se.lexicon.g58todoapp.service.TodoService;

import java.util.List;

@RequestMapping("api/todos")
@RestController
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    @Operation(summary = "Get all todos", description = "Retrieves a list of all todo items")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved todo list")

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TodoDto> getAllTodos() {
        return todoService.findAll();
    }


    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDto todoDto) {

        todoService.create(todoDto);

        return ResponseEntity.status(201).build();

    }


}
