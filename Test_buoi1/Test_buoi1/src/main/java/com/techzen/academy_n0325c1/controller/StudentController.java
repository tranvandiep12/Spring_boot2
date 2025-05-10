package com.techzen.academy_n0325c1.controller;

import com.techzen.academy_n0325c1.dto.ApiResponse;
import com.techzen.academy_n0325c1.exception.AppException;
import com.techzen.academy_n0325c1.exception.Errorcode;
import com.techzen.academy_n0325c1.model.Student;
import com.techzen.academy_n0325c1.repository.StudentRepository;
import com.techzen.academy_n0325c1.service.IStudentService;
import com.techzen.academy_n0325c1.service.StudentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@AllArgsConstructor // Constructor đây nè
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentController {

    // Cách 2: Tiêm thông qua Constructor
    private final IStudentService studentService; // DI thông qua Constructor
//    IStudentService studentService ;
//    @Autowired
//    public StudentController(IStudentService studentService) {
//        this.studentService = studentService;
//    }




    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getStudents() {
//        return ResponseEntity.status(HttpStatus.OK).body(student);
        return ResponseEntity.ok(ApiResponse.<List<Student>>builder()
                .data(studentService.finAll())
                .build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> save(@RequestBody Student student) {
        return ResponseEntity.ok(
                ApiResponse.<Student>builder()
                        .data(studentService.save(student))
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getStudent(@PathVariable("id") int id) throws AppException {
        Student student = studentService.findById(id);
        if (student == null) {
            throw new AppException(Errorcode.STUDENT_NOT_EXITS);
        }
        return ResponseEntity.ok(ApiResponse.<Student>builder()
                .data(student)
                .build());
    }
};

// Không nên viết code ỏ đây vì đây là trường hợp hiếm (ngoại lệ)
//        return ResponseEntity.status(Errorcode.STUDENT_NOT_EXITS.getStatus()).body(
//                ApiResponse.<Student>builder()
//                        .code(Errorcode.STUDENT_NOT_EXITS.getCode())
//                        .message(Errorcode.STUDENT_NOT_EXITS.getMessage())
//                        .build()
//        );

