package fr.eni.geekoquizz.bo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;
import android.arch.persistence.room.TypeConverters;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.geekoquizz.tools.TimestampConverter;

@Parcel
@Entity(tableName = "Questions", foreignKeys = {
        @ForeignKey(entity = Quizz.class, childColumns = "question_quizzId", parentColumns = "quizz_id")
})
public class Question implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "question_id")
    private int id;

    @ColumnInfo(name = "question_intitule")
    private String intitule;

    @TypeConverters({TimestampConverter.class})
    @ColumnInfo(name = "question_dateCrea")
    private Date dateCrea;

    @TypeConverters({TimestampConverter.class})
    @ColumnInfo(name = "question_dateModif")
    private Date dateModif;

    @ColumnInfo(name = "question_image")
    private String image;

    @ColumnInfo(name = "question_nbUse")
    private int nbUse;

    @ColumnInfo(name = "question_nbCorrectUse")
    private int nbCorrectUse;

    @ColumnInfo(name = "question_temps")
    private int temps;

    @Ignore
    private List<Reponse> reponses;

    @Ignore
    private Quizz quizz;

    @ColumnInfo(name = "question_quizzId")
    private int quizzId;

    public Question() {
        this.dateCrea = new Date();
        this.dateModif = new Date();
        this.reponses = new ArrayList<>();
        this.reponses.add(new Reponse());
        this.reponses.add(new Reponse());
        this.reponses.add(new Reponse());
        this.reponses.add(new Reponse());
    }

    public Question(Quizz quizz) {
        this();
        this.quizz = quizz;
        quizz.getQuestions().add(this);
    }

    public Question(String intitule, Date dateCrea, Date dateModif, String image, int nbUse, int nbCorrectUse, List<Reponse> reponses) {
        this.intitule = intitule;
        this.dateCrea = dateCrea;
        this.dateModif = dateModif;
        this.image = image;
        this.nbUse = nbUse;
        this.nbCorrectUse = nbCorrectUse;
        this.reponses = reponses;
    }

    public Question(int id, String intitule, Date dateCrea, Date dateModif, String image, int nbUse, int nbCorrectUse, List<Reponse> reponses, int temps, int quizzId) {
        this.id = id;
        this.intitule = intitule;
        this.dateCrea = dateCrea;
        this.dateModif = dateModif;
        this.image = image;
        this.nbUse = nbUse;
        this.nbCorrectUse = nbCorrectUse;
        this.reponses = reponses;
        this.temps = temps;
        this.quizzId = quizzId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNbUse() {
        return nbUse;
    }

    public void setNbUse(int nbUse) {
        this.nbUse = nbUse;
    }

    public int getNbCorrectUse() {
        return nbCorrectUse;
    }

    public void setNbCorrectUse(int nbCorrectUse) {
        this.nbCorrectUse = nbCorrectUse;
    }

    public int getTemps() {
        return temps;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }

    public List<Reponse> getReponses() {
        return reponses;
    }

    public void setReponses(List<Reponse> reponses) {
        this.reponses = reponses;
    }

    public Quizz getQuizz() {
        return quizz;
    }

    public void setQuizz(Quizz quizz) {
        this.quizz = quizz;
    }

    public int getQuizzId() {
        return quizzId;
    }

    public void setQuizzId(int quizzId) {
        this.quizzId = quizzId;
    }

    public boolean hasMultipleResponses() {
        return getNbCorrectResponse() > 1;
    }

    public int getNbCorrectResponse() {
        int nbCorrectResponse = 0;
        for (Reponse reponse: reponses) {
            if(reponse.isCorrect()) nbCorrectResponse++;
        }
        return nbCorrectResponse;
    }

    public Reponse getReponseById(int id) {
        Reponse reponse = null;
        int i = 0;
        while(i < reponses.size() && reponses.get(i).getId() != id) {
            i++;
        }

        if(i < reponses.size()) {
            reponse = reponses.get(i);
        }
        return reponse;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", intitule='" + intitule + '\'' +
                ", dateCrea=" + dateCrea +
                ", dateModif=" + dateModif +
                ", image='" + image + '\'' +
                ", nbUse=" + nbUse +
                ", nbCorrectUse=" + nbCorrectUse +
                ", temps=" + temps +
                ", reponses=" + reponses +
                '}';
    }
}
