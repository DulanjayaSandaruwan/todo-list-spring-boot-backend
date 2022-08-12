package com.todolist.todolistspringbootbackend.util;

import com.todolist.todolistspringbootbackend.dto.ToDoDTO;
import com.todolist.todolistspringbootbackend.dto.UserDTO;
import com.todolist.todolistspringbootbackend.entity.ToDo;
import com.todolist.todolistspringbootbackend.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * @author : D.D.Sandaruwan <dulanjayasandaruwan1998@gmail.com>
 * @Since : 12/08/2022
 **/
@Component
public class EntityDTOConversion {
    private ModelMapper mapper;
    public EntityDTOConversion(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public User getUserEntity(UserDTO userDTO){
        return mapper.map(userDTO, User.class);
    }
    public UserDTO getUserDTO(User user){
        return mapper.map(user,UserDTO.class);
    }
    public ToDo getToDoEntity (ToDoDTO toDoDTO){
        return mapper.map(toDoDTO, ToDo.class);
    }
    public ToDoDTO getToDoDTO(ToDo toDOEntity){
        return mapper.typeMap(ToDo.class, ToDoDTO.class)
                .addMapping(toDO->toDO.getUser().getId(),ToDoDTO::setUserId)
                .map(toDOEntity);
    }
}
