package com.todolist.todolistspringbootbackend.controller;

import com.todolist.todolistspringbootbackend.dto.ToDoDTO;
import com.todolist.todolistspringbootbackend.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

/**
 * @author : D.D.Sandaruwan <dulanjayasandaruwan1998@gmail.com>
 * @Since : 12/08/2022
 **/

@RestController
@RequestMapping("api/v1/users/{userId:[A-Fa-f0-9\\-]{36}}/todo")
public class ToDoController {

    @Autowired
    public ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json",produces = "application/json")
    public ToDoDTO addToDo(@PathVariable String userId, @RequestBody @Valid ToDoDTO toDo, Errors errors){
        if (errors.hasFieldErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,errors.getFieldErrors().get(0).getDefaultMessage());
        }
        if(!userId.equals(toDo.getUserId()))
            throw new ResponseStatusException(HttpStatus.CONFLICT,"User Id is mismatch");
        return toDoService.saveToDo(toDo);

    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{toDoId:\\d+}")
    public void deleteToDo(@PathVariable String userId,@PathVariable int toDoId){
        toDoService.deleteToDo(userId, toDoId);

    }
    @GetMapping(produces = "application/json")
    public List<ToDoDTO> grtAllNotes(@PathVariable String userId){
        return toDoService.getAllToDos(userId);
    }
}
