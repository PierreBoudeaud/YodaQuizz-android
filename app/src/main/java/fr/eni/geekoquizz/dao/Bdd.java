package fr.eni.geekoquizz.dao;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import fr.eni.geekoquizz.bo.Question;
import fr.eni.geekoquizz.bo.Quizz;
import fr.eni.geekoquizz.bo.QuizzTheme;
import fr.eni.geekoquizz.bo.Reponse;
import fr.eni.geekoquizz.bo.Statistique;
import fr.eni.geekoquizz.bo.Theme;
import fr.eni.geekoquizz.bo.Type;
import fr.eni.geekoquizz.bo.Utilisateur;

@Database(entities = {Quizz.class, Utilisateur.class, Statistique.class, Theme.class, Question.class, Type.class, Reponse.class, QuizzTheme.class}, version = 2)
public abstract class Bdd extends RoomDatabase {
    private static Bdd INSTANCE;

    public abstract QuizzDAO quizzDAO();
    public abstract UtilisateurDAO utilisateurDAO();
    public abstract StatistiqueDAO statistiqueDAO();
    public abstract QuestionDAO questionDAO();
    public abstract ThemeDAO themeDAO();
    public abstract TypeDAO typeDAO();
    public abstract ReponseDAO reponseDAO();
    public abstract QuizzThemeDAO quizzThemeDAO();

    public static synchronized Bdd getInstance(Context context) {
        if (INSTANCE == null) {
            context.deleteDatabase("GeekoQuizz");
            INSTANCE = Room.databaseBuilder(context, Bdd.class, "GeekoQuizz")
                    .addCallback(roomCallBack)
                    .build();
        }
        return INSTANCE;
    }

    private static Callback roomCallBack = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
