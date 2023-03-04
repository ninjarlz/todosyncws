package pl.tul.todosyncws.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.tul.todosyncws.data.Todo;
import pl.tul.todosyncws.repository.TodoRepository;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoRepository todoRepository;

    @GetMapping(value = "todos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Todo>> getTodos() {
        List<Todo> todos = todoRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .location(getCurrentURI())
                .body(todos);
    }

    private URI getCurrentURI() {
        return ServletUriComponentsBuilder.fromCurrentRequestUri()
                .build()
                .toUri();
    }
}
