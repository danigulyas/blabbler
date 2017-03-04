package com.danigu.web;

import com.danigu.web.blab.Blab;
import com.danigu.web.blab.BlabRepository;
import com.danigu.web.support.EmbeddedDataSourceConfig;
import com.danigu.web.config.RootConfig;
import com.danigu.web.config.WebConfig;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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

    @Autowired protected WebApplicationContext context;
    @Autowired protected BlabRepository blabRepository;
    protected MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    protected Blab getBlab() {
        Blab blab = new Blab();

        blab.setContent("examplecontentblab11223.");

        return blabRepository.save(blab);
    }
}
