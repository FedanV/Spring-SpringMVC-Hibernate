package com.foxminded.vitaliifedan.task10.dao.impl;

import com.foxminded.vitaliifedan.task10.dao.AbstractCrudDao;
import com.foxminded.vitaliifedan.task10.dao.AudienceDao;
import com.foxminded.vitaliifedan.task10.models.schedules.Audience;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Component
public class AudienceDaoImpl extends AbstractCrudDao<Audience, Integer> implements AudienceDao {

    private static final Logger logger = LoggerFactory.getLogger(AudienceDaoImpl.class);

    private final EntityManager entityManager;

    @Autowired
    public AudienceDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    protected Audience create(Audience entity) {
        logger.debug("Start creating audience {}", entity.getRoomNumber());
        entityManager.persist(entity);
        logger.debug("Finish creating audience {}", entity.getRoomNumber());
        return entity;
    }

    @Override
    protected Audience update(Audience entity) {
        logger.debug("Start updating Audience {}", entity.getRoomNumber());
        entityManager.merge(entity);
        logger.debug("Finish updating Audience {}", entity.getRoomNumber());
        return entity;
    }

    @Override
    public Boolean delete(Integer id) {
        logger.debug("Start deleting Audience id={}", id);
        Audience audience = entityManager.find(Audience.class, id);
        entityManager.remove(audience);
        logger.debug("Finish deleting Audience id={}", id);
        return true;
    }

    @Override
    public List<Audience> getAll() {
        return entityManager.createQuery("SELECT a FROM Audience a", Audience.class).getResultList();
    }

    @Override
    public Optional<Audience> getById(Integer id) {
        return Optional.ofNullable(entityManager.find(Audience.class, id));
    }

    @Override
    public Optional<Audience> findAudienceByRoomNumber(Integer roomNumber) {
        try {
            return Optional.ofNullable(entityManager.createQuery("SELECT a FROM Audience a WHERE a.roomNumber=:roomNumber", Audience.class)
                    .setParameter("roomNumber", roomNumber)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
