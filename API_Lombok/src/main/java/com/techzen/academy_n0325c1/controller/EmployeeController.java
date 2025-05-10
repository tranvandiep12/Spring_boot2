package com.techzen.academy_n0325c1.controller;

import com.techzen.academy_n0325c1.exception.AppException;
import com.techzen.academy_n0325c1.model.Employee;
import com.techzen.academy_n0325c1.response.JsonResponse;
import com.techzen.academy_n0325c1.constant.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    // Dữ liệu mẫu giả lập thay cho database
    private final List<Employee> employees = new ArrayList<>();

    @GetMapping
    public ResponseEntity<?> getAll() {
        return JsonResponse.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") UUID id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(JsonResponse::<Employee>ok)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXISTED));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Employee employee) {
        employee.setId(UUID.randomUUID());
        employees.add(employee);
        return JsonResponse.created(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID id, @RequestBody Employee employee) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(e -> {
                    e.setName(employee.getName());
                    e.setDob(employee.getDob());
                    e.setGender(employee.getGender());
                    e.setSalary(employee.getSalary());
                    e.setPhone(employee.getPhone());
                    return JsonResponse.ok(e);
                })
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXISTED));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(e -> {
                    employees.remove(e);
                    return JsonResponse.noContent();
                })
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXISTED));
    }
}
