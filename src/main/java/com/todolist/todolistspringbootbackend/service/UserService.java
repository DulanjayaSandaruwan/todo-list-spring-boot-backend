package com.todolist.todolistspringbootbackend.service;

import com.todolist.todolistspringbootbackend.dto.UserDTO;
import com.todolist.todolistspringbootbackend.exception.DuplicateEmailException;
import com.todolist.todolistspringbootbackend.exception.NotFoundException;

/**
 * @author : D.D.Sandaruwan <dulanjayasandaruwan1998@gmail.com>
 * @Since : 12/08/2022
 **/
public interface UserService {
    UserDTO registerUser(UserDTO userDTO) throws DuplicateEmailException;

    void updateUser(UserDTO userDTO) throws NotFoundException;

    UserDTO getUserInfo(String userId) throws NotFoundException;

    void deleteUser(String userId) throws NotFoundException;
}
