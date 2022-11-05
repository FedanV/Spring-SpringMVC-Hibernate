package com.foxminded.vitaliifedan.task10.services.validators;

import com.foxminded.vitaliifedan.task10.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseValidationService {

    private final CourseService courseService;

    @Autowired
    public CourseValidationService(CourseService courseService) {
        this.courseService = courseService;
    }

    public String validateCourseName(String name) {
        String message = "";
        if(courseService.findCourseByName(name).isPresent()) {
            message = String.format("Course with name %s exists", name);
        }
        return message;
    }
}
