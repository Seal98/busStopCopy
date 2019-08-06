package by.duallab.task.dao;

import java.util.List;

public interface Dao<T> {

    T get(int id);

    void set(List<T> l);

    List<T> getAll();

    void save(T t);

    void delete(T t);
}