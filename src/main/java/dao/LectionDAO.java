package dao;

import hibernate.LectionEntity;

import java.util.Collection;

public interface LectionDAO {
    void addLection(LectionEntity entity);
    void mergeLection(LectionEntity entity);
    Collection<LectionEntity> getAll();
    LectionEntity getLection(int id);
}
