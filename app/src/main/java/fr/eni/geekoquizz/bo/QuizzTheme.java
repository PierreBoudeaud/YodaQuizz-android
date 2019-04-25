package fr.eni.geekoquizz.bo;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;

import org.parceler.Parcel;

import java.util.List;

@Parcel
@Entity(tableName = "QuizzTheme",
primaryKeys = {"quizzId", "themeId"},
foreignKeys = {
        @ForeignKey(entity = Quizz.class, parentColumns = "quizz_id", childColumns = "quizzId"),
        @ForeignKey(entity = Theme.class, parentColumns = "theme_id", childColumns = "themeId")
})
public class QuizzTheme {
    private int quizzId;
    @Ignore
    private Quizz quizz;

    public int themeId;

    @Ignore
    private Theme theme;


    public QuizzTheme() {
    }

    public QuizzTheme(final Quizz quizz, final Theme theme) {
        this.quizz = quizz;
        this.quizzId = quizz.getId();
        this.theme = theme;
        this.themeId = theme.getId();
    }

    public QuizzTheme(int quizzId, int themeId) {
        this.quizz = new Quizz();
        this.quizz.setId(quizzId);
        this.quizzId = quizzId;
        this.theme = new Theme();
        this.theme.setId(themeId);
        this.themeId = themeId;
    }

    public static void createListTheme(final Quizz quizz, final List<Theme> themes) {
        for(Theme theme : themes){
            quizz.getThemes().add(new QuizzTheme(quizz, theme));
        }
    }

    public int getQuizzId() {
        return quizzId;
    }

    public int getThemeId() {
        return themeId;
    }

    public Quizz getQuizz() {
        return quizz;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setQuizzId(int quizzId) {
        this.quizzId = quizzId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }
}
