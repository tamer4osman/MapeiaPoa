package org.mapeiapoa;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class OcorrenciaControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(new OcorrenciaController()).build();
    }

    @Test
    public void shouldTestController() throws Exception {
        mockMvc.perform(get("/").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(contains("Test"));
    }

    private ResultMatcher contains(String string) {
        return content().string(Matchers.containsString(string));
    }
}