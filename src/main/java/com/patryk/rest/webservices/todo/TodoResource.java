package com.patryk.rest.webservices.todo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TodoResource {

    private final TodoHardcodedService todoHardcodedService = new TodoHardcodedService();


    @GetMapping("/users/{userName}/todos")
    public List<Todo> getAllTodos(@PathVariable String userName){
        return todoHardcodedService.getTodos(userName);
    }

    @GetMapping("/users/{userName}/todos/{id}")
    public Todo getTodos(@PathVariable String userName, @PathVariable long id){
        return todoHardcodedService.getUserTodo(userName, id);
    }

    @DeleteMapping("/users/{userName}/todos/{id}")
    public ResponseEntity<Void> getAllTodos(@PathVariable String userName, @PathVariable long id){
        Todo todo = todoHardcodedService.deleteTodoById(userName, id);

        if(todo == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{userName}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String userName, @PathVariable long id, @RequestBody Todo todo){
        Todo updatedTodo = todoHardcodedService.save(todo, userName);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    @PostMapping("/users/{userName}/todos")
    public ResponseEntity<Void> createTodo(@PathVariable String userName, @RequestBody Todo todo) {
        Todo updatedTodo = todoHardcodedService.save(todo, userName);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(updatedTodo.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

}
