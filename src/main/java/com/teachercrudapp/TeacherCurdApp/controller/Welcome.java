package com.teachercrudapp.TeacherCurdApp.controller;

import com.teachercrudapp.TeacherCurdApp.model.Student;
import com.teachercrudapp.TeacherCurdApp.service.CustomTeacherDetails;
import com.teachercrudapp.TeacherCurdApp.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.beans.ExceptionListener;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/welcome")
public class Welcome {

    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping
    public String showWelcomePage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomTeacherDetails customTeacherDetails = (CustomTeacherDetails) authentication.getPrincipal();

        String name = customTeacherDetails.getFullName();
        String email = customTeacherDetails.getUsername();

        model.addAttribute("name", name);
        model.addAttribute("email", email);

        // todo, add Students list in welcome page.
        List<Student> studentList = studentService.getStudentList();

        model.addAttribute("students", studentList);

        return "welcome";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id, RedirectAttributes model){
        Optional<Student> student = null;
        // find student by id;
        student = studentService.findById(id);
        if(student.isPresent()){
            Student dbStudent = student.get();
            try{
                studentService.deleteStudent(dbStudent);
                model.addFlashAttribute("msg", "Student Deleted Successfully");
            }catch (Exception e){
                model.addFlashAttribute("msg", e.getMessage());
                return "redirect:/welcome?deleteStudentError";
            }
        }
        return "redirect:/welcome?studDeleted";
    }

    @GetMapping("/update/{id}")
    public String showUpdatePage(@PathVariable("id") int id, Model model){
        Optional<Student> optionalStudent = studentService.findById(id);
        Student updateFormStudent = null;
        if(optionalStudent.isPresent()){
            updateFormStudent = optionalStudent.get();
            model.addAttribute("student", updateFormStudent);
        }
        return "studentUpdate";
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute("student") Student student, RedirectAttributes attribute){
        Student updatedStudent = studentService.updateStudent(student);

        if(updatedStudent != null){
            attribute.addFlashAttribute("updated", "Student updated successfully");
        }else{
            attribute.addFlashAttribute("updateError", "Error to update the student");
        }

        return "redirect:/welcome";
    }
}
