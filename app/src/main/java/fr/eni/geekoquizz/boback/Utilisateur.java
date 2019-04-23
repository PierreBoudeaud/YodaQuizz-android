package fr.eni.geekoquizz.boback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utilisateur implements Serializable {
    private int id;

    private String nom;

    private Date date;

    private List<Statistique> statistiques;

    public Utilisateur() {
        this.date = new Date();
    }

    public Utilisateur(String nom) {
        this();
        this.nom = nom;
    }

    public Utilisateur(int id, String nom, Date date) {
        this(nom);
        this.id = id;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Statistique> getStatistiques() {
        return statistiques;
    }

    public void setStatistiques(List<Statistique> statistiques) {
        this.statistiques = statistiques;
    }

    public fr.eni.geekoquizz.bo.Utilisateur toUtilisateur()
    {
        return new fr.eni.geekoquizz.bo.Utilisateur(
          getId(), getNom(), getDate()
        );
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", date=" + date +
                ", statistiques=" + statistiques +
                '}';
    }
}
