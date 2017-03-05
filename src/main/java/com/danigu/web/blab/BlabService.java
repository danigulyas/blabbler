package com.danigu.web.blab;

import com.danigu.web.blab.dto.CreateBlabDto;
import com.danigu.web.user.UserRepository;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.inject.Inject;

/**
 * @author dani
 */
@Service
public class BlabService {

    @Inject private BlabRepository repository;
    @Inject private UserRepository userRepository;

    public Blab create(CreateBlabDto dto) {
        Assert.notNull(dto);
        Blab newBlab = new Blab();

        final String currentUsersUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        newBlab.setOwner(userRepository.findByUsername(currentUsersUsername));
        newBlab.setContent(dto.getContent());

        return repository.saveAndFlush(newBlab);
    }
}
