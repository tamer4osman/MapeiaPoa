package org.mapeiapoa.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapeiapoa.domain.Incident;
import org.mapeiapoa.repository.IncidentRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class IncidentControllerTest {

    private MockMvc mockMvc;
    @Mock
    private IncidentRepository repository;
    @InjectMocks
    private IncidentController controller;

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void shouldFindAll() throws Exception {
        mockMvc.perform(get("/").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(contains("Test"));
    }

    @Test
    public void shouldTestController() throws Exception {
        Incident incident = new Incident("01", "Testing", "", "");
        when(repository.save(incident)).thenReturn(incident);

        mockMvc.perform(post("/").content(toJson(incident)).contentType(APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(contains("01"))
                .andExpect(contains("Testing"));
    }

    private ResultMatcher contains(String string) {
        return content().string(containsString(string));
    }

    private String toJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(object);
        return jsonInString;
    }
}