package com.todolist.todolistspringbootbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author : D.D.Sandaruwan <dulanjayasandaruwan1998@gmail.com>
 * @Since : 12/08/2022
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ToDoDTO implements Serializable {
    private int id;

    @NotBlank(message = "To do can not be an empty value")
    private String todo;

    @Pattern(regexp = "[A-Fa-f0-9\\-]{36}",message = "Invalid user ID")
    private String userId;
}
