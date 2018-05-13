package dao;

import hibernate.UserEntity;

import java.util.Collection;

public interface UserDAO extends AbstractEntityDAO<UserEntity> {
    UserEntity findUserByLogin(String login);
    UserEntity findUserByAuthToken(String authToken);
}
