package fr.eni.geekoquizz.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Quizz implements Serializable {
    private int id;

    private String nom;

    private Date dateCrea;

    private Date dateModif;

    private String description;

    private int version;

    private Utilisateur createur;

    private List<Question> questions;

    private List<Theme> themes;

    private Type type;

    private List<Statistique> statistiques;


    public Quizz() {
        this.version = 0;
        this.dateCrea = new Date();
        this.dateModif = new Date();
    }

    public Quizz(String nom, String description) {
        this();
        this.nom = nom;
        this.description = description;
    }

    public Quizz(int id, String nom, Date dateCrea, Date dateModif, String description, int version, Utilisateur createur, List<Question> questions, List<Theme> themes, Type type, List<Statistique> statistiques) {
        this(nom, description);
        this.id = id;
        this.dateCrea = dateCrea;
        this.dateModif = dateModif;
        this.version = version;
        this.createur = createur;
        this.questions = questions;
        this.themes = themes;
        this.type = type;
        this.statistiques = statistiques;
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

    public Date getDateCrea() {
        return dateCrea;
    }

    public void setDateCrea(Date dateCrea) {
        this.dateCrea = dateCrea;
    }

    public Date getDateModif() {
        return dateModif;
    }

    public void setDateModif(Date dateModif) {
        this.dateModif = dateModif;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Utilisateur getCreateur() {
        return createur;
    }

    public void setCreateur(Utilisateur createur) {
        this.createur = createur;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(List<Theme> themes) {
        this.themes = themes;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Statistique> getStatistiques() {
        return statistiques;
    }

    public void setStatistiques(List<Statistique> statistiques) {
        this.statistiques = statistiques;
    }

    @Override
    public String toString() {
        return "Quizz{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", dateCrea=" + dateCrea +
                ", dateModif=" + dateModif +
                ", description='" + description + '\'' +
                ", version=" + version +
                ", createur=" + createur +
                ", questions=" + questions +
                ", themes=" + themes +
                ", type=" + type +
                ", statistiques=" + statistiques +
                '}';
    }
}
