package dao;

import hibernate.SubscriptionEntity;
import hibernate.UserEntity;

import java.util.Collection;

public interface SubscriptionDAO {
    void addSubscription(SubscriptionEntity entity);
    void mergeSubscription(SubscriptionEntity entity);
    Collection<SubscriptionEntity> getAll();
    SubscriptionEntity getSubscription(int id);
    Collection<SubscriptionEntity> getAllSubscriptionByUser(int userId);
}
