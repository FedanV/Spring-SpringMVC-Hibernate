package com.foxminded.vitaliifedan.task10.services;

import com.foxminded.vitaliifedan.task10.models.schedules.Lecture;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface LectureService {
    List<Lecture> findAll();
    Optional<Lecture> findById(Integer id);
    Lecture create(Lecture lecture);
    Lecture update(Lecture lecture);
    Boolean deletedById(Integer id);

    Map<String, String> getFilledLecture(Integer id);
}
