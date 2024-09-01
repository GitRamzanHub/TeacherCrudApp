package com.teachercrudapp.TeacherCurdApp.service;

import com.teachercrudapp.TeacherCurdApp.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student addNewStudent(Student student);

    boolean isStudentPresent(String email);

    List<Student> getStudentList();

    Optional<Student> findById(int id);

    void deleteStudent(Student student);

    Student updateStudent(Student student);
}
