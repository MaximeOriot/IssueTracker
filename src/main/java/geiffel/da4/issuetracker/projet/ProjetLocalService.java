package geiffel.da4.issuetracker.projet;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import geiffel.da4.issuetracker.issue.Issue;
import geiffel.da4.issuetracker.projet.Projet;
import geiffel.da4.issuetracker.projet.ProjetService;
import geiffel.da4.issuetracker.utils.LocalService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjetLocalService extends LocalService<Projet, Long> implements ProjetService {
    public ProjetLocalService(List<Projet> projets) {
        super(projets);
    }

    @Override
    protected String getIdentifier() {
        return "id";
    }


    @Override
    public List<Projet> getAll() {
        return super.getAll();
    }

    @Override
    public Projet getById(Long id) {
        return this.getByIdentifier(id);
    }

    @Override
    public Projet create(Projet newProjet) throws ResourceAlreadyExistsException{
        try {
            this.findByProperty(newProjet.getId());
            throw new ResourceAlreadyExistsException("Projet", newProjet.getId());
        } catch (ResourceNotFoundException e) {
            this.allValues.add(newProjet);
            return newProjet;
        }
    }

    private IndexAndValue<Projet> findById(Long id) {return super.findByProperty(id);}

    @Override
    public void update(Long id, Projet updatedProjet) {
        IndexAndValue<Projet> found = this.findByProperty(id);
        this.allValues.remove(found.index());
        this.allValues.add(found.index(), updatedProjet);
    }

    @Override
    public void delete(Long id) {
        IndexAndValue<Projet> found = this.findByProperty(id);
        this.allValues.remove(found.index());
    }
}
