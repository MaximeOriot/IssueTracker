package geiffel.da4.issuetracker;

import geiffel.da4.issuetracker.commentaire.Commentaire;
import geiffel.da4.issuetracker.commentaire.CommentaireRepository;
import geiffel.da4.issuetracker.issue.Issue;
import geiffel.da4.issuetracker.issue.IssueRepository;
import geiffel.da4.issuetracker.user.Fonction;
import geiffel.da4.issuetracker.user.User;
import geiffel.da4.issuetracker.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class IssueTrackerStudentsApplication {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private CommentaireRepository commentaireRepository;

    public static void main(String[] args) {
        SpringApplication.run(IssueTrackerStudentsApplication.class, args);
    }

    @Bean
    public CommandLineRunner setUpBDD() {
        User user=new User(4L, "Oe", Fonction.USER);
        Issue issue=new Issue(1L,"test","content",user, Timestamp.valueOf("2023-09-27 00:00:00"),Timestamp.valueOf("2023-09-28 00:00:00"));
        return (args) -> {
            List<User> users = new ArrayList<>(){{
                add(new User(1L, "Machin", Fonction.USER));
                add(new User(2L, "Chose", Fonction.USER));
                add(new User(3L, "Truc", Fonction.DEVELOPPER));
                add(user);
            }};
            userRepository.saveAll(users);
            List<Issue> issues = new ArrayList<>(){{
                add(issue);
            }};
            issueRepository.saveAll(issues);
            List<Commentaire> commentaires = new ArrayList<>(){{
                add(new Commentaire(1L,user,issue,"Commentaire de fou"));
            }};
            commentaireRepository.saveAll(commentaires);
        };
    }



}
