package com.regmi.auth.dao;

import com.regmi.auth.model.RoleEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;

@Repository
public class RoleDaoImpl implements RoleDao {
    EntityManager entityManager;

    @Autowired
    public RoleDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public RoleEntity getRoleByName(String name) {
        Session session = entityManager.unwrap(Session.class);
        Query<RoleEntity> query = session.createQuery("from RoleEntity where name =:uname", RoleEntity.class);
        query.setParameter("uname", name);
        return query.getSingleResult();
    }
}
