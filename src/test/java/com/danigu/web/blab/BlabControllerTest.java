package com.danigu.web.blab;

import com.danigu.web.BaseTest;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author dani
 */
public class BlabControllerTest extends BaseTest {

    @Test
    public void givenAnIdWithoutABlabThenFindByBlabShouldRenderNotFound() throws Exception {
        mockMvc.perform(get("/blab/13"))
                .andExpect(status().isOk())
                .andExpect(view().name("404"));
    }

    @Test
    public void givenAnIdWithExistingBlabThenFindByIdShouldRenderTheSingleBlabPage() throws Exception {
        Blab blab = getBlab();

        mockMvc.perform(get("/blab/" + blab.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("blab"))
                .andExpect(model().attributeExists("blab"))
                .andExpect(model().attribute("blab", equalTo(blab))); // equals() is shitty here
    }
}
