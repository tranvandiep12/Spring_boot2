package com.techzen.academy_n0325c1.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum Errorcode {
    STUDENT_NOT_EXITS(40401, "Student is not exist!", HttpStatus.NOT_FOUND),
    TEACHER_NOT_EXITS(40402, "Teacher is not exist!", HttpStatus.NOT_FOUND);

    int code;
    String message;
    HttpStatus status;

}
