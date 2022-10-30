package com.foxminded.vitaliifedan.task10.dao.impl;

import com.foxminded.vitaliifedan.task10.dao.AbstractCrudDao;
import com.foxminded.vitaliifedan.task10.dao.GroupDao;
import com.foxminded.vitaliifedan.task10.models.groups.Group;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Component
public class GroupDaoImpl extends AbstractCrudDao<Group, Integer> implements GroupDao {

    private static final Logger logger = LoggerFactory.getLogger(GroupDaoImpl.class);

    private final EntityManager entityManager;

    @Autowired
    public GroupDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    protected Group create(Group entity) {
        logger.debug("Start creating Group {}", entity.getGroupName());
        entityManager.persist(entity);
        logger.debug("Finish creating Group {}", entity.getGroupName());
        return entity;
    }

    @Override
    protected Group update(Group entity) {
        logger.debug("Start updating Group {}", entity.getGroupName());
        entityManager.merge(entity);
        logger.debug("Finish updating Group {}", entity.getGroupName());
        return entity;
    }

    @Override
    public Boolean delete(Integer id) {
        logger.debug("Start updating Group id={}", id);
        Group group = entityManager.find(Group.class, id);
        entityManager.remove(group);
        logger.debug("Finish updating Group id={}", id);
        return true;
    }

    @Override
    public List<Group> getAll() {
        return entityManager.createQuery("SELECT g FROM Group g", Group.class).getResultList();
    }

    @Override
    public Optional<Group> getById(Integer id) {
        return Optional.ofNullable(entityManager.find(Group.class, id));
    }

    @Override
    public Optional<Group> findGroupByGroupName(String name) {
        try {
            return Optional.of(entityManager.createQuery("SELECT g FROM Group g WHERE g.groupName=:groupName", Group.class)
                    .setParameter("groupName", name)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

}
