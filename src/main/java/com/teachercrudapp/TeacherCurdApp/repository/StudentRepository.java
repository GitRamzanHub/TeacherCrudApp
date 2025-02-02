package com.teachercrudapp.TeacherCurdApp.repository;

import com.teachercrudapp.TeacherCurdApp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByEmail(String email);
}
