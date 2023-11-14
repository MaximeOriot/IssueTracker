package geiffel.da4.issuetracker.projet;

import com.fasterxml.jackson.databind.ObjectMapper;
import geiffel.da4.issuetracker.exceptions.ExceptionHandlingAdvice;
import geiffel.da4.issuetracker.issue.Issue;
import geiffel.da4.issuetracker.projet.ProjetController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = ProjetController.class)
@Import(ExceptionHandlingAdvice.class)
public class ProjetControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ProjetService projetService;

    private List<Projet> projets;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        projets = new ArrayList<>(){{
            add(new Projet(1L,"projet1"));
            add(new Projet(2L,"projet2"));
            add(new Projet(3L,"projet3"));
            add(new Projet(4L,"projet4"));
            add(new Projet(5L,"projet5"));
        }};
        Mockito.when(projetService.getAll()).thenReturn(projets);
        Mockito.when(projetService.getById(1L)).thenReturn(projets.get(0));
    }

    @Test
    void whenGettingAllReturn5() throws Exception {
        mockMvc.perform(get("/projets")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()
        ).andExpect(jsonPath("$", hasSize(5))
        ).andDo(print());
    }

    @Test
    void whenGetting1LShouldReturnSame() throws Exception{
        mockMvc.perform(get("/projets/1")
                .contentType(MediaType.APPLICATION_JSON));
    }


}
