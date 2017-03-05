package com.danigu.web.user;

import com.danigu.web.BaseTest;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author dani
 */
public class UserControllerTest extends BaseTest {

    @Test
    public void givenCorrectUserDetailsWhenSignUpIsPostedThenAnUserShouldBeCreated() throws Exception {
        final String username = "coolcat01";

        mockMvc.perform(getCreateUserRequest(username, "examplepassword1", "examplepassword1"))
                .andExpect(status().isOk())
                .andExpect(view().name("users/signup"))
                .andExpect(model().attributeExists("successMessage"))
                .andExpect(model().attributeHasNoErrors("user"));

        assertThat(userRepository.existsByUsername(username)).isTrue();
    }

    @Test
    public void givenAUsernameWhichIsAlreadyRegisteredWhenSignUpIsPostedThenAnErrorShouldBeDisplayed() throws Exception {
        User user = getUser();

        mockMvc.perform(getCreateUserRequest(user.getUsername()))
                .andExpect(status().isOk())
                .andExpect(view().name("users/signup"))
                .andExpect(model().attributeDoesNotExist("successMessage"))
                .andExpect(model().attributeHasFieldErrors("user", "username"));

        // Make sure the requested one wasn't inserted to the db.
        userRepository.delete(user);
        assertThat(userRepository.existsByUsername(user.getUsername())).isFalse();
    }

    @Test
    public void givenInvalidValuesWhenSignUpIsPostedThenErrorsShouldBeDisplayed() throws Exception {
        mockMvc.perform(getCreateUserRequest("s", "s", "doesntmatch"))
                .andExpect(status().isOk())
                .andExpect(view().name("users/signup"))
                .andExpect(model().attributeDoesNotExist("successMessage"))
                .andExpect(model().attributeHasFieldErrors("user",
                        "username", "password", "passwordConfirm"));
    }

    protected MockHttpServletRequestBuilder getCreateUserRequest(String username, String password, String passwordConfirm) {
        return post("/users/signup")
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", username)
                .param("password", password)
                .param("passwordConfirm", passwordConfirm);
    }

    protected MockHttpServletRequestBuilder getCreateUserRequest(String username) {
        return getCreateUserRequest(username, "surfingonanuclearexplosion", "surfingonanuclearexplosion");
    }
}
