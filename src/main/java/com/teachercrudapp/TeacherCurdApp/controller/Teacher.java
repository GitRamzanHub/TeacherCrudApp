package com.teachercrudapp.TeacherCurdApp.controller;

import com.teachercrudapp.TeacherCurdApp.service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/register")
public class Teacher {

    @Autowired
    private TeacherServiceImpl teacherService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping
    public String showTeacherRegisterPage(Model model){
        model.addAttribute("teacher", new com.teachercrudapp.TeacherCurdApp.model.Teacher());
        return "teacherRegister";
    }

    @PostMapping()
    public String addTeacher(@ModelAttribute("teacher") com.teachercrudapp.TeacherCurdApp.model.Teacher teacher, RedirectAttributes attribute){
        com.teachercrudapp.TeacherCurdApp.model.Teacher savedTeacher = null;
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        savedTeacher = teacherService.addTeacher(teacher);
        if(savedTeacher != null){
            attribute.addFlashAttribute("success", "Teacher Added");

        }else{
            attribute.addFlashAttribute("error", "Error with adding new Teacher");
        }

        return "redirect:/login";
    }
}
