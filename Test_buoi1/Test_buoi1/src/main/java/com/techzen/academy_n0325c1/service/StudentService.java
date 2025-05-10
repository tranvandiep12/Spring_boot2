package com.techzen.academy_n0325c1.service;

import com.techzen.academy_n0325c1.model.Student;
import com.techzen.academy_n0325c1.repository.IStudentRepository;
import com.techzen.academy_n0325c1.repository.StudentRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
// @Scope("prototype")  Khi tạo ra 1 đối tượng sẽ ra 1 list Mới
// vì vật khi khi get/list/students chỉ lấy ra list mới gồm 3 đối tượng ban đầu
// @Scope("prototype")  là mặc dinh ko cần ghi vào
public class StudentService implements IStudentService{
    IStudentRepository studentRepository;

    public List<Student> finAll() {
        return studentRepository.finAll();
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Student findById(int id) {
        return studentRepository.findById(id);
    }
}
