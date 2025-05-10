package com.techzen.academy_n0325c1.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) // makeFinal = true không được sửa đổi gì cả
public class AppException extends Exception{
    Errorcode errorcode;

    public AppException(Errorcode errorcode) {
        super(errorcode.getMessage());
        this.errorcode = errorcode;
    }
}
