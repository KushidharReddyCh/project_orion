package com.example.project_orion.exceptions;

public class APIException extends RuntimeException{
    private static final long serialVersionUID = 1;

    public APIException(){

    }

    public APIException(String message){
        super(message);
    }
}