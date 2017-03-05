package com.danigu.web.user.validator;

import com.danigu.web.user.UserRepository;
import com.danigu.web.user.dto.CreateUserDto;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.inject.Inject;

/**
 * @author dani
 */
@Component
public class CreateUserDtoValidator implements Validator {

    private static final int REQUIRED_PASSWORD_LENGTH = 8;
    private static final int REQUIRED_USERNAME_LENGTH = 3;

    @Inject
    private UserRepository repository;

    public boolean supports(Class clazz) {
        return CreateUserDto.class.equals(clazz);
    }

    /**
     * {@link ValidationUtils} will not need the user to be referenced to it, the {@link Errors}
     * object is holding a reference to it.
     *
     * There's obviously a sec / "glitch" hole since we're checking lower bounds only.
     */
    public void validate(Object target, Errors e) {
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "username", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "password", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "passwordConfirm", "field.required");

        CreateUserDto dto = (CreateUserDto) target;

        if(dto.getUsername().length() < REQUIRED_USERNAME_LENGTH)
            e.rejectValue("username", "field.tooShort");

        if(dto.getPassword().length() < REQUIRED_PASSWORD_LENGTH)
            e.rejectValue("password", "field.tooShort");

        if(!dto.getPassword().equals(dto.getPasswordConfirm()))
            e.rejectValue("passwordConfirm", "field.mustEqual");

        if(repository.existsByUsername(((CreateUserDto) target).getUsername()))
            e.rejectValue("username", "field.mustBeUnique");
    }
}
