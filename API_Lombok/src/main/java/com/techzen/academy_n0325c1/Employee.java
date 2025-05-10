package com.techzen.academy_n0325c1;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
    UUID id;
    String name;
    LocalDate dob;
    Gender gender;
    Double salary;
    String phone;
}