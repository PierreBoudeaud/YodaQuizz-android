package fr.eni.geekoquizz.bo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.io.Serializable;
import java.util.Date;

import fr.eni.geekoquizz.tools.TimestampConverter;

@Entity(tableName = "Reponses", foreignKeys = {
        @ForeignKey(entity = Question.class, childColumns = "reponse_questionId", parentColumns = "question_id")
})
public class Reponse implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "reponse_id")
    private int id;

    @ColumnInfo(name = "reponse_nom")
    private String nom;

    @TypeConverters({TimestampConverter.class})
    @ColumnInfo(name = "reponse_dateCrea")
    private Date dateCrea;

    @ColumnInfo(name = "reponse_correct")
    private boolean correct;

    @ColumnInfo(name = "reponse_questionId")
    private int questionId;

    public Reponse() {
        this.dateCrea = new Date();
    }



    public Reponse(String nom, boolean correct) {
        this();
        this.nom = nom;
        this.correct = correct;
    }

    public Reponse(int id, String nom, Date dateCrea, boolean correct) {
        this.id = id;
        this.nom = nom;
        this.dateCrea = dateCrea;
        this.correct = correct;
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

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "Reponse{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", dateCrea=" + dateCrea +
                ", correct=" + correct +
                '}';
    }
}
