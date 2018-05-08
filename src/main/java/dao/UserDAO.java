package dao;

import hibernate.UserEntity;

import java.util.Collection;

public interface UserDAO {
    void addUser(UserEntity entity);
    void mergeUser(UserEntity entity);
    void removeUser(UserEntity entity);
    Collection<UserEntity> getAll();
    UserEntity getUser(int id);
    UserEntity findUser(String login);
    UserEntity findUserByAuthToken(String authToken);
}
