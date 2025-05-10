package com.techzen.academy_n0325c1.repository;

import com.techzen.academy_n0325c1.model.Student;

import java.util.List;

public interface IStudentRepository {
     List<Student> finAll();

     Student save(Student student) ;

     Student findById(int id);
}
