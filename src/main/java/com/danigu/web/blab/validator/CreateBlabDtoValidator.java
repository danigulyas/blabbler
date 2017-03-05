package com.danigu.web.blab.validator;

import com.danigu.web.blab.dto.CreateBlabDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author dani
 */
@Component
public class CreateBlabDtoValidator implements Validator {
    public static final int MAXIMUM_LENGTH = 140;

    public boolean supports(Class clazz) {
        return CreateBlabDto.class.equals(clazz);
    }

    public void validate(Object target, Errors e) {
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "content", "field.tooShort");

        CreateBlabDto dto = (CreateBlabDto) target;

        if(dto.getContent().length() > MAXIMUM_LENGTH)
            e.rejectValue("content", "field.tooLong");
    }
}
