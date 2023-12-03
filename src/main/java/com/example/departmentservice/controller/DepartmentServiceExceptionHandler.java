package com.example.departmentservice.controller;

import com.example.departmentservice.Exception.ErrorDetails;
import com.example.departmentservice.Exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class DepartmentServiceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<ErrorDetails> handleResourceNotFoundException
            (ResourceNotFoundException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setErrorCode("DP001");
        errorDetails.setErrorMessage(exception.getMessage());
        errorDetails.setTarget(webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ErrorDetails> handleGlobalException
            (Exception exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setErrorCode("DP500");
        errorDetails.setErrorMessage(exception.getMessage());
        errorDetails.setTarget(webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
