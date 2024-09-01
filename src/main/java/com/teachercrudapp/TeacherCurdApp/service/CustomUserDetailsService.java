package com.teachercrudapp.TeacherCurdApp.service;

import com.teachercrudapp.TeacherCurdApp.model.Teacher;
import com.teachercrudapp.TeacherCurdApp.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private TeacherRepository teacherRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Username Received :"+username);
        Teacher dbTeacher = teacherRepository.findByUsername(username);
        System.out.println("Username with db:" +dbTeacher.getUsername());
        if(dbTeacher != null) return new CustomTeacherDetails(dbTeacher);
        else return new CustomTeacherDetails(dbTeacher);
    }
}
