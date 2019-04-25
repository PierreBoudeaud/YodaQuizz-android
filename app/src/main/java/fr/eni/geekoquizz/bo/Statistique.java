package fr.eni.geekoquizz.bo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.Date;

import fr.eni.geekoquizz.tools.TimestampConverter;

@Parcel
@Entity(tableName = "Statistiques")
public class Statistique implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "statistique_id")
    private int id;

    @TypeConverters({TimestampConverter.class})
    @ColumnInfo(name = "statistique_nom")
    private Date date;

    @ColumnInfo(name = "statistique_nbPoints")
    private int nbPoints;

    @ColumnInfo(name = "statistique_nbCorrect")
    private int nbCorrect;

    @Ignore
    private Quizz quizz;

    @ColumnInfo(name = "statistique_quizzId")
    private int quizzId;

    @Ignore
    private Utilisateur joueur;

    @ColumnInfo(name = "statistique_joueurId")
    private int joueurId;

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

    public int getJoueurId() {
        return joueurId;
    }

    public void setJoueurId(int joueurId) {
        this.joueurId = joueurId;
    }

    public int getQuizzId() {
        return quizzId;
    }

    public void setQuizzId(int quizzId) {
        this.quizzId = quizzId;
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
