package com.example.demo.modelo;

import com.example.demo.validation.PasswordMatches;
import com.example.demo.validation.ValidEmail;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@PasswordMatches
public class UserDto {
    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty(message = "Email should not be empty")
    @ValidEmail
    private String email;
    @NotEmpty(message = "Password should not be empty")
    private String password;
}
