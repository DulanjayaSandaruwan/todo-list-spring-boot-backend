package com.todolist.todolistspringbootbackend.service.impl;

import com.todolist.todolistspringbootbackend.dto.UserDTO;
import com.todolist.todolistspringbootbackend.entity.User;
import com.todolist.todolistspringbootbackend.exception.DuplicateEmailException;
import com.todolist.todolistspringbootbackend.exception.NotFoundException;
import com.todolist.todolistspringbootbackend.repo.UserRepo;
import com.todolist.todolistspringbootbackend.service.UserService;
import com.todolist.todolistspringbootbackend.util.EntityDTOConversion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * @author : D.D.Sandaruwan <dulanjayasandaruwan1998@gmail.com>
 * @Since : 12/08/2022
 **/
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    private final EntityDTOConversion transform;

    public UserServiceImpl(UserRepo userRepo, EntityDTOConversion transform) {
        this.userRepo = userRepo;
        this.transform = transform;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) throws DuplicateEmailException {
        //find out whether it is existed user that going to be saved
        if(userRepo.existsUserByEmail(userDTO.getEmail())) throw new DuplicateEmailException("User already exists");
        //if not
        userDTO.setId(UUID.randomUUID().toString());
        //save and return the result
        return transform.getUserDTO(userRepo.save(transform.getUserEntity(userDTO)));


    }

    @Override
    public void updateUser(UserDTO userDTO) throws NotFoundException {
        Optional<User> optUser = userRepo.findById(userDTO.getId());
        if(!optUser.isPresent()) throw new NotFoundException("No user found");
        optUser.get().setFullName(userDTO.getFullName());
        optUser.get().setPassword(userDTO.getPassword());

    }

    @Override
    public UserDTO getUserInfo(String userId) throws NotFoundException {
        return userRepo.findById(userId).map(transform::getUserDTO)
                .orElseThrow(()->new NotFoundException("Invalid User"));
    }

    @Override
    public void deleteUser(String userId) throws NotFoundException {
        if(!userRepo.existsById(userId))throw new NotFoundException("Invalid UserId");
        userRepo.deleteById(userId);

    }
}
