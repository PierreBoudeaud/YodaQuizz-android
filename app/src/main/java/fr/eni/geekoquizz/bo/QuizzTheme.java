package fr.eni.geekoquizz.bo;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import java.util.List;

@Entity(tableName = "QuizzTheme",
primaryKeys = {"quizzId", "themeId"},
foreignKeys = {
        @ForeignKey(entity = Quizz.class, parentColumns = "quizz_id", childColumns = "quizzId"),
        @ForeignKey(entity = Theme.class, parentColumns = "theme_id", childColumns = "themeId")
})
public class QuizzTheme {
    private final int quizzId;


    public final int themeId;

    public QuizzTheme(final int quizzId, final int themeId) {
        this.quizzId = quizzId;
        this.themeId = themeId;
    }

    public static void createListTheme(final Quizz quizz, final List<Theme> themes) {
        for(Theme theme : themes){
            quizz.getThemes().add(new QuizzTheme(quizz.getId(), theme.getId()));
        }
    }

    public int getQuizzId() {
        return quizzId;
    }

    public int getThemeId() {
        return themeId;
    }
}
