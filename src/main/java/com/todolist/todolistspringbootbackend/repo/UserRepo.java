package com.todolist.todolistspringbootbackend.repo;

import com.todolist.todolistspringbootbackend.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author : D.D.Sandaruwan <dulanjayasandaruwan1998@gmail.com>
 * @Since : 12/08/2022
 **/
public interface UserRepo extends CrudRepository<User, String> {
    boolean existsUserByEmail(String email);
}
