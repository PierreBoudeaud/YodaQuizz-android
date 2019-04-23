package fr.eni.geekoquizz.boback;

import java.io.Serializable;

public class Type implements Serializable {

    private int id;

    private String nom;

    private String description;

    private String icon;

    public Type() {
    }

    public Type(String nom, String description, String icon) {
        this.nom = nom;
        this.description = description;
        this.icon = icon;
    }

    public Type(int id, String nom, String description, String icon) {
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

    public fr.eni.geekoquizz.bo.Type toType()
    {
        return new fr.eni.geekoquizz.bo.Type(
          getId(),getNom(),getDescription(),getIcon()
        );
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
