package geiffel.da4.issuetracker.issue;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import geiffel.da4.issuetracker.issue.IssueRepository;
import geiffel.da4.issuetracker.issue.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Qualifier("jpa")
public class IssueJPAService implements IssueService{

    @Autowired
    private IssueRepository issueRepository;

    @Override
    public List<Issue> getAll() {
        return issueRepository.findAll();
    }

    @Override
    public Issue getByCode(Long l) {
        Optional<Issue> issue = issueRepository.findById(l);
        if (issue.isPresent()) {
            return issue.get();
        } else {
            throw new ResourceNotFoundException("Issue", l);
        }
    }

    @Override
    public Issue create(Issue newIssue) {
        if(issueRepository.existsById(newIssue.getCode())){
            throw new ResourceAlreadyExistsException("Issue",newIssue.getCode());
        }
        else {
            return issueRepository.save(newIssue);
        }
    }

    @Override
    public void update(Long code, Issue updatedIssue) {
        if(issueRepository.existsById(code)){
            throw new ResourceNotFoundException("Issue",code);
        }
        else {
            issueRepository.save(updatedIssue);
        }
    }

    @Override
    public void delete(Long code) {
        issueRepository.deleteById(code);
    }
}
