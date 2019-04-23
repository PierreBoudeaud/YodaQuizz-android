package fr.eni.geekoquizz.boback;


import java.io.Serializable;
import java.util.Date;

public class Reponse implements Serializable {

    private int id;

    private String nom;

    private Date dateCrea;

    private boolean correct;

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

    public fr.eni.geekoquizz.bo.Reponse toReponse()
    {
        return new fr.eni.geekoquizz.bo.Reponse(
          getId(),getNom(), getDateCrea(), isCorrect()
        );
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
