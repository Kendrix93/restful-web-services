package com.patryk.rest.webservices.todo;

import com.patryk.rest.webservices.exceptions.MyRunTimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TodoJpaResource {

    private final TodoHardcodedService todoHardcodedService = new TodoHardcodedService();

    @Autowired
    private TodoJpaRepository jpaRepository;


    @GetMapping("/jpa/users/{userName}/todos")
    public List<Todo> getAllTodos(@PathVariable String userName){
        return jpaRepository.findByUserName(userName); //todo może nie zadziałać bo naming zły
//        return todoHardcodedService.getTodos(userName);
    }

    @GetMapping("/jpa/users/{userName}/todos/{id}")
    public Todo getTodos(@PathVariable String userName, @PathVariable long id){
        return  jpaRepository.findById(id).orElseThrow(()-> new MyRunTimeException("wyjebalo w chuj"));
    }

    @DeleteMapping("/jpa/users/{userName}/todos/{id}")
    public ResponseEntity<Void> getAllTodos(@PathVariable String userName, @PathVariable long id){
        Todo todo = todoHardcodedService.deleteTodoById(userName, id);

        if(todo == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/jpa/users/{userName}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String userName, @PathVariable long id, @RequestBody Todo todo){
        Todo updatedTodo = todoHardcodedService.save(todo, userName);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    @PostMapping("/jpa/users/{userName}/todos")
    public ResponseEntity<Void> createTodo(@PathVariable String userName, @RequestBody Todo todo) {
        Todo updatedTodo = todoHardcodedService.save(todo, userName);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(updatedTodo.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

}
