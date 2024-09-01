package com.teachercrudapp.TeacherCurdApp.service;

import com.teachercrudapp.TeacherCurdApp.model.Student;
import com.teachercrudapp.TeacherCurdApp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student addNewStudent(Student student) {
        String email = student.getEmail();
        if(!isStudentPresent(email)){
            // todo, calculate total, percentage & grade logic here....

            return calculateAndAddStudent(student);
        }else{
            return null;
        }
    }

    private Student calculateAndAddStudent(Student student) {
        double total = student.getHindiMark() + student.getMarathiMark() +
                student.getMathsMark() + student.getScienceMark() +
                student.getEnglishMark();

        double percentage = (total / 500) * 100;
        String formattedPercentage = String.format("%.2f", percentage);
        double percentageWithTwoDecimals = Double.parseDouble(formattedPercentage);

        String result = "Pass";
        if(student.getEnglishMark() < 40 || student.getHindiMark() < 40 ||
                student.getMathsMark() < 40 || student.getMarathiMark() < 40 ||
                student.getScienceMark() < 40){
            result = "Fail";
        }

        String grade;

        if(percentage >= 90){
            grade = "A+";
        }else if (percentage >= 80){
            grade = "A";
        }else if(percentage >= 70){
            grade = "B";
        }else if(percentage >= 60){
            grade = "C";
        }else if(percentage >= 50){
            grade = "D";
        }else if(percentage >= 40){
            grade = "E";
        }else {
            grade = "F";
        }

        student.setTotal(total);
        student.setPercentage(percentageWithTwoDecimals);
        student.setResult(result);
        student.setGrade(grade);

        return studentRepository.save(student);
    }

    @Override
    public boolean isStudentPresent(String email) {
        // if student present return true
        // if student not present return false;
        Student stud = studentRepository.findByEmail(email);

        if(stud == null) return false;
        else return true;
    }

    @Override
    public List<Student> getStudentList() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(int id) {
        return studentRepository.findById(id);

    }

    @Override
    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }

    @Override
    public Student updateStudent(Student student) {
        return calculateAndAddStudent(student);
    }
}
