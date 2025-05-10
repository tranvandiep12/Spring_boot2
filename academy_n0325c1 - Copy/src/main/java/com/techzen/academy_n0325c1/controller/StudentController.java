package com.techzen.academy_n0325c1.controller;

import com.techzen.academy_n0325c1.dto.ApiResponse;
import com.techzen.academy_n0325c1.exception.AppException;
import com.techzen.academy_n0325c1.exception.ErrorCode;
import com.techzen.academy_n0325c1.model.Student;
import com.techzen.academy_n0325c1.service.IStudentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@AllArgsConstructor // Constructor đây nè!
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentController {
    // new: Tạo ra đối tượng => Lập trình viên phải chủ động quản lý đối tượng
    // Bean: Đối tượng => Spring tạo ra và quản lý
    IStudentService studentService; // DI thông qua Constructor

    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getStudents() {
        return ResponseEntity.ok(ApiResponse.<List<Student>>builder()
                .data(studentService.findAll())
                .build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> save(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<Student>builder()
                        .data(studentService.save(student))
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getStudent(@PathVariable("id") int id) {
        Student student = studentService.findById(id);

        if (student == null) {
            throw new AppException(ErrorCode.STUDENT_NOT_EXIST);
        }

        return ResponseEntity.ok(ApiResponse.<Student>builder()
                .data(student)
                .build());
    }
}
