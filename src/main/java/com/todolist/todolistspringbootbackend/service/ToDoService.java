package com.todolist.todolistspringbootbackend.service;

import com.todolist.todolistspringbootbackend.dto.ToDoDTO;
import com.todolist.todolistspringbootbackend.exception.NotFoundException;

import java.util.List;

/**
 * @author : D.D.Sandaruwan <dulanjayasandaruwan1998@gmail.com>
 * @Since : 12/08/2022
 **/
public interface ToDoService {
    ToDoDTO saveToDo(ToDoDTO todo) throws NotFoundException;

    List<ToDoDTO> getAllToDos(String userId);

    void deleteToDo(String userId,int toDoId) throws NotFoundException;
}
