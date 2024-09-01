package com.teachercrudapp.TeacherCurdApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "teacher")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullName;
    private String username;
    private String password;
    private String dateOfBirth;
    private String mobileNumber;
    private String designation;
    private String role = "ADMIN";
}
