package com.patryk.rest.webservices.restfulwebservices;

import com.patryk.rest.webservices.exceptions.MyRunTimeException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HelloWorldController {

    @GetMapping(path = "/hello-world")
    public String getHelloWorld(){
        return "Hello World Patryk";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean getHelloWorldBean(){
        return new HelloWorldBean("Hello Patryk bean");
    }


    @GetMapping(path = "/hello-world-name/{name}")
    public HelloWorldBean getHelloWorldName(@PathVariable String name){
        return new HelloWorldBean("Hello path variable " + name);
    }

    @GetMapping(path = "/hello-world-exception/{name}")
    public HelloWorldBean getHelloWorldException(@PathVariable String name){
        throw new MyRunTimeException("Hello World Exception jest tutaj ziaaaa " + name);
    }

}
