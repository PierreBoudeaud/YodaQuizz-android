package fr.eni.geekoquizz.bo;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;

import java.util.List;

@Entity(tableName = "QuizzTheme",
primaryKeys = {"quizzId", "themeId"},
foreignKeys = {
        @ForeignKey(entity = Quizz.class, parentColumns = "quizz_id", childColumns = "quizzId"),
        @ForeignKey(entity = Theme.class, parentColumns = "theme_id", childColumns = "themeId")
})
public class QuizzTheme {
    private final int quizzId;
    @Ignore
    private final Quizz quizz;

    public final int themeId;

    @Ignore
    private final Theme theme;

    public QuizzTheme(final Quizz quizz, final Theme theme) {
        this.quizz = quizz;
        this.quizzId = quizz.getId();
        this.theme = theme;
        this.themeId = theme.getId();
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
}
