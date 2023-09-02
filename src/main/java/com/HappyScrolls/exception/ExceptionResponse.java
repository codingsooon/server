package com.HappyScrolls.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
public class ExceptionResponse {

    private Date timestamp;
    private String message;
    private String dtails;

}
