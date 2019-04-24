package fr.eni.geekoquizz.bo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.geekoquizz.tools.TimestampConverter;

@Entity(tableName = "Quizz", foreignKeys = {
        @ForeignKey(entity = Utilisateur.class, parentColumns = "utilisateur_id", childColumns = "quizz_utilisateurId"),
        @ForeignKey(entity = Type.class, parentColumns = "type_id", childColumns = "quizz_typeId")
})
public class Quizz implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "quizz_id")
    private int id;

    @ColumnInfo(name = "quizz_nom")
    private String nom;

    @TypeConverters({TimestampConverter.class})
    @ColumnInfo(name = "quizz_dateCrea")
    private Date dateCrea;

    @TypeConverters({TimestampConverter.class})
    @ColumnInfo(name = "quizz_dateModif")
    private Date dateModif;

    @ColumnInfo(name = "quizz_description")
    private String description;

    @ColumnInfo(name = "quizz_version")
    private int version;

    @ColumnInfo(name = "quizz_utilisateurId")
    private int utilisateurId;

    @Ignore
    private Utilisateur utilisateur;

    @Ignore
    private List<Question> questions;

    @Ignore
    private List<QuizzTheme> themes;

    @ColumnInfo(name = "quizz_typeId")
    private int typeId;

    @Ignore
    private Type type;

    @Ignore
    private List<Statistique> statistiques;

    @ColumnInfo(name = "quizz_difficulte")
    private float difficulte;


    public Quizz() {
        this.version = 0;
        this.dateCrea = new Date();
        this.dateModif = new Date();
        this.questions = new ArrayList<>();
        this.themes = new ArrayList<>();
    }

    public Quizz(String nom, String description, float difficulte) {
        this();
        this.nom = nom;
        this.description = description;
        this.difficulte = difficulte;
    }

    public Quizz(int id, String nom, float difficulte, Date dateCrea, Date dateModif, String description, int version, Utilisateur createur, List<Question> questions, Type type, List<Statistique> statistiques) {
        this(nom, description, difficulte);
        this.id = id;
        this.dateCrea = dateCrea;
        this.dateModif = dateModif;
        this.version = version;
        this.questions = questions;
        this.statistiques = statistiques;
    }

    public Quizz(int id, String nom, float difficulte ,Date dateCrea, Date dateModif, String description, int version, Utilisateur createur, List<QuizzTheme> themes, Type type) {
        this(nom, description,difficulte);
        this.id = id;
        this.dateCrea = dateCrea;
        this.dateModif = dateModif;
        this.version = version;
        this.themes = themes;
        this.type = type;
    }

    public Quizz(int id, String nom, float difficulte ,Date dateCrea, Date dateModif, String description, int version, Utilisateur createur, List<Question> questions, List<Theme> themes, Type type, List<Statistique> statistiques) {
        this(nom, description,difficulte);
        this.id = id;
        this.dateCrea = dateCrea;
        this.dateModif = dateModif;
        this.version = version;
        this.questions = questions;
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

    public float getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(float difficulte) {
        this.difficulte = difficulte;
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<QuizzTheme> getThemes() {
        return themes;
    }

    public void setThemes(List<QuizzTheme> themes) {
        this.themes = themes;
    }

    public List<Statistique> getStatistiques() {
        return statistiques;
    }

    public void setStatistiques(List<Statistique> statistiques) {
        this.statistiques = statistiques;
    }

    public int getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(int utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void addTheme(Theme theme) {
        this.themes.add(new QuizzTheme(this, theme));
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
                ", questions=" + questions +
                ", themes=" + themes +
                ", statistiques=" + statistiques +
                '}';
    }
}
