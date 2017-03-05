package com.danigu.web.user.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author dani
 */
@Data
public class CreateUserDto {
    @NotNull
    @Size(min = 3)
    private String username;

    @NotNull
    @Size(min = 3)
    private String password;

    @NotNull
    @Size(min = 3)
    private String passwordConfirm;
}
