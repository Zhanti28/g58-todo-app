package se.lexicon.g58todoapp.service;

import org.springframework.stereotype.Service;
import se.lexicon.g58todoapp.dto.TodoDto;
import se.lexicon.g58todoapp.entity.Person;
import se.lexicon.g58todoapp.entity.Todo;
import se.lexicon.g58todoapp.repo.PersonRepository;
import se.lexicon.g58todoapp.repo.TodoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    private final PersonRepository personRepository;

    public TodoService(TodoRepository todoRepository, PersonRepository personRepository) {
        this.todoRepository = todoRepository;
        this.personRepository = personRepository;
    }

    public TodoDto create(TodoDto todoDto) {

        // Convert dto to entity
        Todo todo = new Todo(todoDto.title(), todoDto.description(), todoDto.completed(), todoDto.dueDate());

        //save entity
        Todo save = todoRepository.save(todo);


        // convert to dto.

        return TodoDto.convertToDto(save);

    }

    public List<TodoDto> findAll() {
        return todoRepository.findAll().stream()
                .map(TodoDto::convertToDto)
                .toList();
    }


    public TodoDto findById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Todo Found with that ID. 👀"));
        return TodoDto.convertToDto(todo);
    }

    public TodoDto update(Long id, TodoDto todoDto) {

        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        existingTodo.setTitle(todoDto.title());
        existingTodo.setDescription(todoDto.description());
        existingTodo.setCompleted(todoDto.completed());
        existingTodo.setDueDate(todoDto.dueDate());

        if (todoDto.assignedToId() != null) {
            Person person = personRepository.findById(todoDto.assignedToId())
                    .orElseThrow(() -> new RuntimeException("Person not Found"));
            existingTodo.setAssignedTo(person);
        } else {
            existingTodo.setAssignedTo(null);
        }
        Todo updatedTodo = todoRepository.save(existingTodo);

        return TodoDto.convertToDto(updatedTodo);
    }

    public void delete(Long id) {
        todoRepository.deleteById(id);
    }

    public List<TodoDto> findByAssignedTo_Id(Long assignedToId) {
        return todoRepository.findByAssignedTo_Id(assignedToId).stream()
                .map(TodoDto::convertToDto)
                .collect(Collectors.toList());
    }
    public List<TodoDto> findByCompleted(boolean completed) {
        return todoRepository.findByCompleted(completed).stream()
                .map(TodoDto::convertToDto)
                .collect(Collectors.toList());
    }
    public List<TodoDto> findOverdueTodos() {
        return todoRepository.findByDueDateBeforeAndCompletedFalse(LocalDateTime.now()).stream()
                .map(TodoDto::convertToDto)
                .collect(Collectors.toList());
    }


}
