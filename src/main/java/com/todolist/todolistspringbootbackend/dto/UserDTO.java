package com.todolist.todolistspringbootbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

/**
 * @author : D.D.Sandaruwan <dulanjayasandaruwan1998@gmail.com>
 * @Since : 12/08/2022
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    @Null(message = "User ID will be automatically generated")
    private String id;

    @NotNull(message = "User name cannot be an empty value")
    @Pattern(regexp = "[A-Za-z ]+")
    private String fullName;

    @NotNull(message = "Email cannot be an empty value")
    @Email
    private String email;

    @NotBlank(message = "Password cannot be an empty value")
    @Length(min = 5)
    private String password;
}
