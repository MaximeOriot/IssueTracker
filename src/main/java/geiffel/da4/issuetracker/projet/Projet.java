package geiffel.da4.issuetracker.projet;


import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;
public class Projet {

    private Long id;
    private String nom;


    public Projet(Long id, String nom)
    {
        this.id=id;
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
