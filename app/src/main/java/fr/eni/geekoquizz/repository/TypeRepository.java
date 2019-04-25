package fr.eni.geekoquizz.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import fr.eni.geekoquizz.bo.Type;
import fr.eni.geekoquizz.dao.Bdd;
import fr.eni.geekoquizz.dao.TypeDAO;

public class TypeRepository {
    TypeDAO typeDAO;

    public TypeRepository(Context context) {
        Bdd bdd = Bdd.getInstance(context);
        typeDAO = bdd.typeDAO();
    }

    public LiveData<List<Type>> get() {
        return typeDAO.get();
    }

    public LiveData<Type> get(int id) {
        return typeDAO.get(id);
    }

    public void insert(Type type) {
        TypeRepository.AsyncInsert async = new TypeRepository.AsyncInsert();
        async.execute(type);
    }

    public void insertAll(List<Type> types) {
        TypeRepository.AsyncMultipleInsert async = new TypeRepository.AsyncMultipleInsert();
        async.execute(types);
    }

    public class AsyncInsert extends AsyncTask<Type, Void, Void> {
        @Override
        protected Void doInBackground(Type... types) {
            typeDAO.insert(types[0]);
            return null;
        }
    }

    public class AsyncMultipleInsert extends AsyncTask<List<Type>, Void, Void> {
        @Override
        protected Void doInBackground(List<Type>... types) {
            typeDAO.insertAll(types[0]);
            return null;
        }
    }
}
