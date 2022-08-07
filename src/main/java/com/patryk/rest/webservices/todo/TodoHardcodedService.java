package com.patryk.rest.webservices.todo;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoHardcodedService {

    private static final List<Todo> todos = new ArrayList<>();
    private static long idCounter = 0;
    private static final String PATRYK = "patryk";

    static {
        todos.add(new Todo(idCounter++, PATRYK, "uczyc sie sprina", new Date(), false));
        todos.add(new Todo(idCounter++, PATRYK, "uczyc sie boota", new Date(), false));
        todos.add(new Todo(idCounter++, PATRYK, "learn microservices", new Date(), false));
    }

    public List<Todo> getTodos(String userName) {
        return getUserTodos(userName);
    }

    private List<Todo> getUserTodos(String userName) {
        return todos.stream()
                .filter(todo -> todo.getUserName().equals(userName))
                .toList();
    }

    public Todo deleteTodoById(String userName, long id){
        List<Todo> userTodos = getUserTodos(userName);
        Todo todo = findById(userTodos, id);
        if(todo == null){
            return null;
        }
        todos.remove(todo);
        return todo;
    }

    public Todo getUserTodo(String userName, long id){
        List<Todo> userTodos = getUserTodos(userName);
        return userTodos.stream().filter(todo -> todo.getId() == id).findFirst().orElse(null);
    }

    private Todo findById(List<Todo> userTodos, long id) {
        Optional<Todo> first = userTodos.stream().filter(todo -> todo.getId() == id).findFirst();
        return first.orElse(null);
    }

    public Todo save(Todo todo, String name){
        if(todo.getId() == -1) {
            todo.setUserName(name);
            todo.setId(idCounter++);
        }else {
            todo.setUserName(name);
            deleteTodoById(name, todo.getId());
        }
        todos.add(todo);
        return todo;
    }


}
