package com.foxminded.vitaliifedan.task10.repositories;

import com.foxminded.vitaliifedan.task10.models.schedules.Audience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AudienceRepository extends JpaRepository<Audience, Integer> {

    Optional<Audience> findAudienceByRoomNumber(Integer roomNumber);

}
