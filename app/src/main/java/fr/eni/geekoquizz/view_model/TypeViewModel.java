package fr.eni.geekoquizz.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import fr.eni.geekoquizz.bo.Type;
import fr.eni.geekoquizz.repository.TypeRepository;

public class TypeViewModel extends AndroidViewModel {
    private LiveData<List<Type>> types;
    TypeRepository typeRepository;

    public TypeViewModel(@NonNull Application application) {
        super(application);
        typeRepository = new TypeRepository(application);
        init();
    }

    private void init() {
        types = typeRepository.get();
    }

    public void insert(Type type) {
        typeRepository.insert(type);
    }

    public LiveData<List<Type>> get() {
        return types;
    }

    public LiveData<Type> get(int id) {
        return typeRepository.get(id);
    }
}
