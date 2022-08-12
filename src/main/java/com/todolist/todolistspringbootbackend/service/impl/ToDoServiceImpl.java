package com.todolist.todolistspringbootbackend.service.impl;

import com.todolist.todolistspringbootbackend.dto.ToDoDTO;
import com.todolist.todolistspringbootbackend.entity.ToDo;
import com.todolist.todolistspringbootbackend.entity.User;
import com.todolist.todolistspringbootbackend.exception.NotFoundException;
import com.todolist.todolistspringbootbackend.exception.UnauthorizedAccessException;
import com.todolist.todolistspringbootbackend.repo.ToDoRepo;
import com.todolist.todolistspringbootbackend.repo.UserRepo;
import com.todolist.todolistspringbootbackend.service.ToDoService;
import com.todolist.todolistspringbootbackend.util.EntityDTOConversion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : D.D.Sandaruwan <dulanjayasandaruwan1998@gmail.com>
 * @Since : 12/08/2022
 **/

@Service
@Transactional
public class ToDoServiceImpl implements ToDoService {
    public final UserRepo userRepo;

    public final EntityDTOConversion transform;

    public final ToDoRepo toDoRepo;

    public ToDoServiceImpl(UserRepo userRepo, EntityDTOConversion transform, ToDoRepo toDoRepo) {
        this.userRepo = userRepo;
        this.transform = transform;
        this.toDoRepo = toDoRepo;
    }

    @Override
    public ToDoDTO saveToDo(ToDoDTO todo) throws NotFoundException {
        ToDo toDoEntity = transform.getToDoEntity(todo);
        toDoEntity.setUser(getUser(todo.getUserId()));
        return transform.getToDoDTO(toDoRepo.save(toDoEntity));
    }

    @Override
    public List<ToDoDTO> getAllToDos(String userId) {
        return toDoRepo.findAllToDosByUser(getUser(userId))
                .stream().map(transform::getToDoDTO).collect(Collectors.toList());

    }

    @Override
    public void deleteToDo(String userId, int toDoId) throws NotFoundException {
        ToDo toDO =
                toDoRepo.findById(toDoId).orElseThrow(() -> new NotFoundException("Invalid Note"));
        if(getUser(userId) != toDO.getUser()){
            toDoRepo.deleteById(toDoId);
            throw new UnauthorizedAccessException("Not allow to delete the todo details");
        }

    }

    //because of the neediness of the user
    private User getUser(String userId){
        return userRepo.findById(userId).orElseThrow(()->new NotFoundException("User not found"));
    }
}
