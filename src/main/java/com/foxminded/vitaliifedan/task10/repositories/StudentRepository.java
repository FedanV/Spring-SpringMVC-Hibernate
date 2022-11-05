package com.foxminded.vitaliifedan.task10.repositories;

import com.foxminded.vitaliifedan.task10.models.persons.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Student s set s.group.id = :groupId where s.id = :studentId")
    void addGroupToStudent(Integer groupId, Integer studentId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Student s set s.group.id = null where s.id = :studentId")
    void removeStudentFromGroup(Integer studentId);

}
