package dao;

import hibernate.AbstractEntity;
import hibernate.UserEntity;

import java.util.Collection;

public interface AbstractEntityDAO <T extends AbstractEntity> {
    void add(T entity);
    void merge(T entity);
    void remove(T entity);
    Collection<T> getAll();
    T get(int id);

    default AbstractEntity getParent(int parentId){
        return null;
    }
    default Collection<T> getAllByParent(AbstractEntity parent){
        return null;
    }
}
