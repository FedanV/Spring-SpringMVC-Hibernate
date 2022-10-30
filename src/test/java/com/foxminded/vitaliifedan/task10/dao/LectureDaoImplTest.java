package com.foxminded.vitaliifedan.task10.dao;

import com.foxminded.vitaliifedan.task10.containers.BaseDaoTest;
import com.foxminded.vitaliifedan.task10.dao.impl.LectureDaoImpl;
import com.foxminded.vitaliifedan.task10.models.groups.Group;
import com.foxminded.vitaliifedan.task10.models.persons.User;
import com.foxminded.vitaliifedan.task10.models.schedules.Audience;
import com.foxminded.vitaliifedan.task10.models.schedules.Course;
import com.foxminded.vitaliifedan.task10.models.schedules.Lecture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@DataJpaTest
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE
)
@Sql(scripts = {"/databaseTestData/audience_table_test_data.sql", "/databaseTestData/course_table_test_data.sql",
        "/databaseTestData/groups_table_test_data.sql", "/databaseTestData/users_table_test_data.sql",
        "/databaseTestData/lecture_table_test_data.sql"}, executionPhase = BEFORE_TEST_METHOD)
class LectureDaoImplTest extends BaseDaoTest {

    @Autowired
    private EntityManager entityManager;

    private LectureDao lectureDao;

    @BeforeEach
    void init() {
        lectureDao = new LectureDaoImpl(entityManager);
    }

    @Test
    void should_CreateLecture() {
        Lecture lecture = Lecture.builder()
                .id(1)
                .course(Course.builder().id(1).build())
                .teacher(User.builder().id(1).build())
                .lectureDate(LocalDate.now())
                .group(Group.builder().id(1).build())
                .pairNumber(4)
                .audience(Audience.builder().id(1).build())
                .build();
        Assertions.assertNotNull(lectureDao.save(lecture));
    }

    @Test
    void should_UpdateLecture() {
        Lecture lecture = Lecture.builder()
                .id(2)
                .course(Course.builder().id(1).build())
                .teacher(User.builder().id(1).build())
                .lectureDate(LocalDate.now())
                .group(Group.builder().id(1).build())
                .pairNumber(4)
                .audience(Audience.builder().id(1).build())
                .build();
        Assertions.assertNotNull(lectureDao.save(lecture));
    }

    @Test
    void should_DeleteLecture() {
        Assertions.assertTrue(lectureDao.delete(3));
    }

    @Test
    void should_getAllLectures() {
        Assertions.assertEquals(3, lectureDao.getAll().size());
    }

    @Test
    void should_getLectureById() {
        Assertions.assertTrue(lectureDao.getById(3).isPresent());
    }

}
