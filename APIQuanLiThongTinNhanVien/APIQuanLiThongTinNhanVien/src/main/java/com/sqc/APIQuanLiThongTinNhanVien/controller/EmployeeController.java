package com.sqc.APIQuanLiThongTinNhanVien.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final List<Employee> employees = new ArrayList<>(
            Arrays.asList(
                    new Employee(UUID.randomUUID(), "Hoàng Văn Hải", LocalDate.of(1990, 1, 15),
                            Gender.MALE, 15000000.00, "0975123542"),
                    new Employee(UUID.randomUUID(), "Trần Thị Hoài", LocalDate.of(1985, 5, 28),
                            Gender.FEMALE, 14500000.00, "0967689684"),
                    new Employee(UUID.randomUUID(), "Lê Văn Sỹ", LocalDate.of(1992, 3, 10),
                            Gender.MALE, 15500000.00, "0988881100"),
                    new Employee(UUID.randomUUID(), "Phạm Duy Khánh", LocalDate.of(1988, 7, 5),
                            Gender.FEMALE, 14000000.00, "0965555333"),
                    new Employee(UUID.randomUUID(), "Hoàng Văn Quý", LocalDate.of(1995, 9, 25),
                            Gender.MALE, 15200000.00, "0975388668")
            )
    );

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable("id") UUID id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        employee.setId(UUID.randomUUID());
        employees.add(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable("id") UUID id, @RequestBody Employee employee) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(e -> {
                    e.setName(employee.getName());
                    e.setDob(employee.getDob());
                    e.setGender(employee.getGender());
                    e.setSalary(employee.getSalary());
                    e.setPhone(employee.getPhone());
                    return ResponseEntity.ok(e);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(s -> {
                    employees.remove(s);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
