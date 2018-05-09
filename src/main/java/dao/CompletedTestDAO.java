package dao;

import hibernate.CompletedTestEntity;
import hibernate.UserEntity;

import java.util.Collection;

public interface CompletedTestDAO {
    void addCompletedTest(CompletedTestEntity entity);
    void mergeCompletedTest(CompletedTestEntity entity);
    void removeCompletedTest(CompletedTestEntity entity);
    Collection<CompletedTestEntity> getAll();
    CompletedTestEntity getCompletedTest(int idUser, int idTest);
}
