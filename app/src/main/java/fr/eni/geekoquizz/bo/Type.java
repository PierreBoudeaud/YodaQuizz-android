package fr.eni.geekoquizz.bo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.parceler.Parcel;

import java.io.Serializable;

@Parcel
@Entity(tableName = "Types")
public class Type implements Serializable {

    @PrimaryKey()
    @ColumnInfo(name = "type_id")
    private int id;

    @ColumnInfo(name = "type_nom")
    private String nom;

    @ColumnInfo(name = "type_description")
    private String description;

    @ColumnInfo(name = "type_icon")
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
