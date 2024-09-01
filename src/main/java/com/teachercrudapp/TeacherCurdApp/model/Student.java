package com.teachercrudapp.TeacherCurdApp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
@Table(name = "student")
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studId;

    private String fullName;

    private String standard;

    private String section;

    private String dateOfBirth;

    private String mobileNumber;

    private String email;

    private double englishMark;

    private double mathsMark;

    private double scienceMark;

    private double hindiMark;

    private double marathiMark;

    private double total;

    private double percentage;

    private String result;

    private String grade;


}
