package fr.eni.geekoquizz.bo;

import java.io.Serializable;
import java.util.Date;

public class Statistique implements Serializable {

    private int id;

    private Date date;

    private int nbPoints;

    private int nbCorrect;

    private Quizz quizz;

    private Utilisateur joueur;

    public Statistique() {
    }

    public Statistique(Date date, int nbPoints, int nbCorrect, Quizz quizz, Utilisateur joueur) {
        this.date = date;
        this.nbPoints = nbPoints;
        this.nbCorrect = nbCorrect;
        this.quizz = quizz;
        this.joueur = joueur;
    }

    public Statistique(int id, Date date, int nbPoints, int nbCorrect, Quizz quizz, Utilisateur joueur) {
        this.id = id;
        this.date = date;
        this.nbPoints = nbPoints;
        this.nbCorrect = nbCorrect;
        this.quizz = quizz;
        this.joueur = joueur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public void setNbPoints(int nbPoints) {
        this.nbPoints = nbPoints;
    }

    public int getNbCorrect() {
        return nbCorrect;
    }

    public void setNbCorrect(int nbCorrect) {
        this.nbCorrect = nbCorrect;
    }

    public Quizz getQuizz() {
        return quizz;
    }

    public void setQuizz(Quizz quizz) {
        this.quizz = quizz;
    }

    public Utilisateur getJoueur() {
        return joueur;
    }

    public void setJoueur(Utilisateur joueur) {
        this.joueur = joueur;
    }

    @Override
    public String toString() {
        return "Statistique{" +
                "id=" + id +
                ", date=" + date +
                ", nbPoints=" + nbPoints +
                ", nbCorrect=" + nbCorrect +
                ", quizz=" + quizz +
                ", joueur=" + joueur +
                '}';
    }
}
