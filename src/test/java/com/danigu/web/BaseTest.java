package com.danigu.web;

import com.danigu.web.blab.Blab;
import com.danigu.web.blab.BlabRepository;
import com.danigu.web.support.EmbeddedDataSourceConfig;
import com.danigu.web.config.RootConfig;
import com.danigu.web.config.WebConfig;
import com.danigu.web.user.User;
import com.danigu.web.user.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import static junit.framework.TestCase.assertNull;

/**
 *
 * @author dani
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class, WebConfig.class, EmbeddedDataSourceConfig.class })
@WebAppConfiguration
@Ignore("Base class.")
public class BaseTest {

    @Inject protected WebApplicationContext context;
    @Inject protected BlabRepository blabRepository;
    @Inject protected UserRepository userRepository;
    @Inject protected PasswordEncoder passwordEncoder;
    protected MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    protected Blab getBlab() {
        Blab blab = new Blab();

        blab.setContent(RandomStringUtils.random(20));

        return blabRepository.save(blab);
    }

    protected User getUser() {
        return getUser(RandomStringUtils.random(15), RandomStringUtils.random(10));
    }

    protected User getUser(String username, String password) {
        User user = new User();

        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        return userRepository.saveAndFlush(user);
    }
}
