package com.danigu.web.blab;

import com.danigu.web.blab.dto.CreateBlabDto;
import com.danigu.web.blab.validator.CreateBlabDtoValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * @author dani
 */
@Controller
@RequestMapping("/")
public class BlabController {

    @Inject private BlabService service;
    @Inject private BlabRepository repository;
    @Inject private CreateBlabDtoValidator createValidator;

    @GetMapping
    public String getFeed(Model model) {
        model.addAttribute("blabs", repository.findAllByOrderByCreatedAtDesc());
        model.addAttribute("blab", new Blab());
        return "blabs/feed";
    }

    /**
     * TODO: Redirect here to make sure POST doesn't happen again on reload.
     * @param dto
     * @param binding
     * @param model
     * @return
     */
    @PostMapping
    public String addBlab(@ModelAttribute("blab") CreateBlabDto dto, BindingResult binding, Model model) {
        createValidator.validate(dto, binding);

        if(!binding.hasErrors()) {
            service.create(dto);
            return "redirect:/";
        }

        return "blabs/feed";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Long id, Model model) {
        Blab blab = repository.findOne(id);
        if(blab == null) return "404";

        model.addAttribute("blab", blab);
        return "blab";
    }
}
