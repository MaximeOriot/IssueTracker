package geiffel.da4.issuetracker.projet;

import geiffel.da4.issuetracker.issue.Issue;

import java.util.List;

public interface ProjetService {
    List<Projet> getAll();

    Projet getById(Long id);

    Projet create(Projet newProjet);

    void update(Long id, Projet updatedProjet);

    void delete(Long id);
}
