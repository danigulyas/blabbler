package com.danigu.web.user;

import com.danigu.web.user.dto.CreateUserDto;
import com.danigu.web.user.validator.CreateUserDtoValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * @author dani
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Inject private UserService service;
    @Inject private CreateUserDtoValidator validator;

    @GetMapping("/signup")
    public String getSignUp(Model model) {
        model.addAttribute("user", new CreateUserDto());
        return "users/signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute("user") CreateUserDto request, BindingResult result, Model model) {
        validator.validate(request, result);

        if(!result.hasErrors()) {
            service.create(request);
            // TODO: Do this with a message class which thymeleaf would talk with.
            model.addAttribute("successMessage", "Registration successful, you may log in now.");
        }

        return "users/signup";
    }
}
