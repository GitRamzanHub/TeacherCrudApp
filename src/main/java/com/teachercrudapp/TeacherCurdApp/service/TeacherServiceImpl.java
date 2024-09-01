package com.teachercrudapp.TeacherCurdApp.service;


import com.teachercrudapp.TeacherCurdApp.model.Teacher;
import com.teachercrudapp.TeacherCurdApp.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Teacher addTeacher(Teacher teacher) {
        Teacher savedTeacher = null;
        savedTeacher = teacherRepository.save(teacher);
        if(savedTeacher != null){
            return savedTeacher;
        }
        return null;
    }
}
