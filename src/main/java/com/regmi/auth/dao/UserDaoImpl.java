package com.regmi.auth.dao;

import com.regmi.auth.model.UserEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;

@Repository
public class UserDaoImpl implements UserDao {
    EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public UserEntity getUserById(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(UserEntity.class, id);
    }

    @Override
    public void saveUser(UserEntity user) {
        Session session = entityManager.unwrap(Session.class);
        session.save(user);
    }

    @Override
    public UserEntity getUserByName(String name) {
        Session session = entityManager.unwrap(Session.class);
        Query<UserEntity> query = session.createQuery("from UserEntity where username =:uname", UserEntity.class);
        query.setParameter("uname", name);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void deleteUserById(int id) {
        Session session = entityManager.unwrap(Session.class);
        UserEntity user = session.get(UserEntity.class, id);
        session.delete(user);
    }

    @Override
    public void updateUser(UserEntity userEntity) {
        Session session = entityManager.unwrap(Session.class);
        session.update(userEntity);
    }
}
