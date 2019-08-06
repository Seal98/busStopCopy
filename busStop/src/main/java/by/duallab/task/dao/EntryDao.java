package by.duallab.task.dao;

import by.duallab.task.beans.Entry;

import java.util.ArrayList;
import java.util.List;

public class EntryDao implements Dao<Entry> {

    private List<Entry> entries;

    private EntryDao(){
        super();
        entries = new ArrayList<>();
    }

    private static final class SingletonHandler{
        public static final Dao entity = new EntryDao();
    }

    public static Dao getInstance(){
        return SingletonHandler.entity;
    }

    @Override
    public Entry get(int id) {
        return entries.get(id);
    }

    @Override
    public void set(List<Entry> entries) {
        this.entries = entries;
    }

    @Override
    public List<Entry> getAll() {
        return entries;
    }

    @Override
    public void save(Entry entry) {
        entries.add(entry);
    }

    @Override
    public void delete(Entry entry) {
        entries.remove(entry);
    }

}
