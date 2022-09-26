package com.foxminded.vitaliifedan.task10.services.validators;

import com.foxminded.vitaliifedan.task10.dao.CourseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseValidationService {

    private final CourseDao courseDao;

    @Autowired
    public CourseValidationService(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public String validateCourseName(String name) {
        String message = "";
        if(courseDao.findByCourseName(name).isPresent()) {
            message = String.format("Course with name %s exists", name);
        }
        return message;
    }
}
