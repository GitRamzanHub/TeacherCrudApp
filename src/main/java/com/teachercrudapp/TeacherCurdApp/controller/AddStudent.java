package com.teachercrudapp.TeacherCurdApp.controller;

import com.teachercrudapp.TeacherCurdApp.model.Student;
import com.teachercrudapp.TeacherCurdApp.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/addStudent")
public class AddStudent {

    @Autowired
    private StudentServiceImpl studentService;


    @GetMapping
    public String showAddStudentForm(){
        return "addStudent";
    }

    @PostMapping
    public String addStudent(@ModelAttribute("student")Student student, Model model){
        Student savedStudent = studentService.addNewStudent(student);

        if(savedStudent != null){
            return "redirect:/welcome?success";
        }else{
            return "/addStudent?error";
        }

    }
}
