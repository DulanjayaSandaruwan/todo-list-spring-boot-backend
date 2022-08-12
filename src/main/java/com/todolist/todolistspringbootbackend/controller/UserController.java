package com.todolist.todolistspringbootbackend.controller;

import com.todolist.todolistspringbootbackend.dto.UserDTO;
import com.todolist.todolistspringbootbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author : D.D.Sandaruwan <dulanjayasandaruwan1998@gmail.com>
 * @Since : 12/08/2022
 **/

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json",produces = "application/json")
    public UserDTO registerUser(@RequestBody @Validated UserDTO user, Errors errors){
        if(errors.hasFieldErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,errors.getFieldErrors()
                    .get(0).getDefaultMessage());
        }
        return userService.registerUser(user);

    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(path = "/{userId}",consumes = "application/json")
    public void updateUser(@PathVariable String userId,@RequestBody UserDTO user, Errors errors){
        if(errors.hasFieldErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,errors.getFieldErrors()
                    .get(0).getDefaultMessage());
        }
        user.setId(userId);
        userService.updateUser(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{userId:[A-Fa-f0-9\\-]{36}}")
    public void deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
    }
    @GetMapping(path = "/{userId:[A-Fa-f0-9\\-]{36}}",produces = "application/json")
    public UserDTO getUserInfo(@PathVariable String userId){
        return userService.getUserInfo(userId);
    }
}
