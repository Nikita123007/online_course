package dao;

import hibernate.DiplomaEntity;

import java.util.Collection;

public interface DiplomaDAO {
    void addDiploma(DiplomaEntity entity);
    void mergeDiploma(DiplomaEntity entity);
    Collection<DiplomaEntity> getAll();
    DiplomaEntity getDiploma(int id);
}
