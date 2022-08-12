package com.todolist.todolistspringbootbackend.advisor;

import com.todolist.todolistspringbootbackend.exception.DuplicateEmailException;
import com.todolist.todolistspringbootbackend.exception.NotFoundException;
import com.todolist.todolistspringbootbackend.exception.UnauthorizedAccessException;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author : D.D.Sandaruwan <dulanjayasandaruwan1998@gmail.com>
 * @Since : 10/08/2022
 **/

@RestControllerAdvice
public class AppAdvisor {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateEmailException.class)
    public String handleDuplicateEmailException(DuplicateEmailException dx){
        dx.initCause(new ResponseStatusException(HttpStatus.CONFLICT,dx.getMessage()));
        return dx.getMessage();
    }
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(NotFoundException.class)
    public String handleUserNotFoundException(NotFoundException nx){
        nx.initCause(new ResponseStatusException(HttpStatus.CONFLICT,nx.getMessage()));
        return nx.getMessage();
    }
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(UnauthorizedAccessException.class)
    public String handleUnauthorizedException(NotFoundException nx){
        nx.initCause(new ResponseStatusException(HttpStatus.FORBIDDEN, nx.getMessage()));
        return nx.getMessage();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
