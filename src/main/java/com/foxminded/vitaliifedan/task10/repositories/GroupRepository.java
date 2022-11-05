package com.foxminded.vitaliifedan.task10.repositories;

import com.foxminded.vitaliifedan.task10.models.groups.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    Optional<Group> findGroupByGroupName(String groupName);
}
