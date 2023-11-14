package geiffel.da4.issuetracker.projet;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import geiffel.da4.issuetracker.issue.Issue;
import geiffel.da4.issuetracker.issue.IssueLocalService;
import geiffel.da4.issuetracker.projet.ProjetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = ProjetService.class)
public class ProjetServiceTest {
    private ProjetService projetService;
    private List<Projet> projets;

    @BeforeEach
    void setUp() {
        projets = new ArrayList<>(){{
            add(new Projet(1L,"projet1"));
            add(new Projet(2L,"projet2"));
            add(new Projet(3L,"projet3"));
            add(new Projet(4L,"projet4"));
            add(new Projet(5L,"projet5"));
        }};
        projetService = new ProjetLocalService(projets);
    }

    @Test
    void whenGettingAll_shouldHave5Projets() {
        assertEquals(5, projetService.getAll().size(), "There should be 5 projects in total");
    }

    @Test
    void whenQueryingId_shouldHaveSameProjet() {
        assertAll(
                () -> assertEquals(projets.get(4), projetService.getById(5L)),
                () -> assertEquals(projets.get(0), projetService.getById(1L))
        );

    }

    @Test
    void whenCreatingProjet_shouldHaveIncreasedSize_andShouldGetIt() {
        Projet new_projet = new Projet(7L,"projet7");
        int initial_size = projets.size();

        assertAll(
                () -> assertEquals(new_projet, projetService.create(new_projet)),
                () -> assertEquals(initial_size+1, projets.size()),
                () -> assertEquals(new_projet, projets.get(initial_size))
        );
    }

    @Test
    void whenCreatingWithSameId_shouldReturnEmpty_andNotIncrease() {
        Projet projet = projets.get(0);
        int initial_size = projets.size();

        assertAll(
                () -> assertThrows(ResourceAlreadyExistsException.class, ()->projetService.create(projet)),
                () -> assertEquals(initial_size, projets.size())
        );
    }

    @Test
    void whenUpdating_shouldContainModifiedProjet() {
        Projet projetToModify1 = projets.get(2);
        String newName = "Modified nom";
        Projet updateNameProjet = new Projet(projetToModify1.getId(), newName);

        projetService.update(projetToModify1.getId(), updateNameProjet);

        assertAll(
                () -> assertEquals(newName, projetService.getById(projetToModify1.getId()).getNom())
        );
    }

    @Test
    void whenDeleting_shouldBeSmaller() {
        int expected_size = projets.size()-1;
        Long id = 4L;
        projetService.delete(id);
        assertAll(
                () -> assertEquals(expected_size, projetService.getAll().size()),
                () -> assertThrows(ResourceNotFoundException.class, ()->projetService.getById(id))
        );
    }
}
