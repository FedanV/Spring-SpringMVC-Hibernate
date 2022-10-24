package com.foxminded.vitaliifedan.task10.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO {

    @NotEmpty
    @Size(min = 3, max = 30, message = "Login should contain 3 to 30 characters")
    private String login;
    @NotEmpty
    @Size(min = 3, max = 30, message = "Password should contain 3 to 30 characters")
    private String password;

}
