package com.sqc.academy.controller;

import com.sqc.academy.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private List<Student> students = new ArrayList<>(
            Arrays.asList(
                    new Student(1, "Hồ Văn Trung", 9.6),
                    new Student(2, "Ngọc Mai", 9.8),
                    new Student(3, "Phương", 9.7)
            )
    );

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getByIdStudents(@PathVariable int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return ResponseEntity.ok(new ApiResponse<>(20001, "thành công", student));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(40401, "Không tìm thấy", null));
    }

    @PostMapping
    public ResponseEntity<Student> save(@RequestBody Student student) {
        student.setId((int) (Math.random() * 100000));
        students.add(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }
}
