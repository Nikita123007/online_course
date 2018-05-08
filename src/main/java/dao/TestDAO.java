package dao;

import hibernate.TestEntity;

import java.util.Collection;

public interface TestDAO {
    void addTest(TestEntity entity);
    void mergeTest(TestEntity entity);
    void removeTest(TestEntity entity);
    Collection<TestEntity> getAll();
    TestEntity getTest(int id);
}
