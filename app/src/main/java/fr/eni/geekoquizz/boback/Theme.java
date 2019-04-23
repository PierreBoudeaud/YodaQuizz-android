package fr.eni.geekoquizz.boback;

import java.io.Serializable;

public class Theme implements Serializable {

    private int id;

    private String nom;

    private String description;

    private String icon;

    public Theme() {
    }

    public Theme(String nom, String description, String icon) {
        this.nom = nom;
        this.description = description;
        this.icon = icon;
    }

    public Theme(int id, String nom, String description, String icon) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.icon = icon;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public fr.eni.geekoquizz.bo.Theme toTheme()
    {
        return new fr.eni.geekoquizz.bo.Theme(
            getId(),getNom(),getDescription(),getIcon()
        );
    }

    @Override
    public String toString() {
        return "Theme{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
