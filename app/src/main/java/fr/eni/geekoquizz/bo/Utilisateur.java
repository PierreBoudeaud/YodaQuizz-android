package fr.eni.geekoquizz.bo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;
import android.arch.persistence.room.TypeConverters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.geekoquizz.tools.TimestampConverter;

@Entity(tableName = "Utilisateurs")
public class Utilisateur implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "utilisateur_id")
    private int id;

    @ColumnInfo(name = "utilisateur_nom")
    private String nom;

    @TypeConverters({TimestampConverter.class})
    @ColumnInfo(name = "utilisateur_date")
    private Date date;

    @Ignore
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
        this.statistiques =  new ArrayList<>();
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
