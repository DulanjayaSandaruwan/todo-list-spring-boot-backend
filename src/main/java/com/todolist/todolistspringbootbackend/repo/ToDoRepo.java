package com.todolist.todolistspringbootbackend.repo;

import com.todolist.todolistspringbootbackend.entity.ToDo;
import com.todolist.todolistspringbootbackend.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author : D.D.Sandaruwan <dulanjayasandaruwan1998@gmail.com>
 * @Since : 12/08/2022
 **/
public interface ToDoRepo extends CrudRepository<ToDo, Integer> {
    List<ToDo> findAllToDosByUser(User user);
    long countToDosByUser(User user);
}
