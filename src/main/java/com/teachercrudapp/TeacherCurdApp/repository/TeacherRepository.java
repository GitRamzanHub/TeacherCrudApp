package com.teachercrudapp.TeacherCurdApp.repository;

import com.teachercrudapp.TeacherCurdApp.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher findByUsername(String username);
}
