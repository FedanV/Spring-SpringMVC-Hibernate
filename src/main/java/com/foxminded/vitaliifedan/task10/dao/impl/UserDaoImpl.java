package com.foxminded.vitaliifedan.task10.dao.impl;

import com.foxminded.vitaliifedan.task10.dao.AbstractCrudDao;
import com.foxminded.vitaliifedan.task10.dao.UserDao;
import com.foxminded.vitaliifedan.task10.models.persons.User;
import com.foxminded.vitaliifedan.task10.models.persons.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoImpl extends AbstractCrudDao<User, Integer> implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private final EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    protected User create(User entity) {
        logger.debug("Start creating user with login {}", entity.getLogin());
        entityManager.persist(entity);
        logger.debug("Finish creating user with login {}", entity.getLogin());
        return entity;
    }

    @Override
    protected User update(User entity) {
        logger.debug("Start updating user with login {}", entity.getLogin());
        entityManager.merge(entity);
        return entity;
    }

    @Override
    public Boolean delete(Integer id) {
        logger.debug("Start deleting user with id={}", id);
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
        logger.debug("Finish deleting user with id={}", id);
        return true;
    }

    @Override
    public List<User> getAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public Optional<User> getById(Integer id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public List<User> getUsersByUserType(UserType userType) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.userType=:userType", User.class)
                .setParameter("userType", userType)
                .getResultList();
    }

    @Override
    public Optional<User> findUserByPhone(String phone) {
        try {
            return Optional.ofNullable(entityManager.createQuery("SELECT u FROM User u WHERE u.phone=:phone", User.class)
                    .setParameter("phone", phone)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        try {
            return Optional.ofNullable(entityManager.createQuery("SELECT u FROM User u WHERE u.login=:login", User.class)
                    .setParameter("login", login)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

}
