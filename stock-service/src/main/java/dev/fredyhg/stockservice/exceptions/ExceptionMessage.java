package dev.fredyhg.stockservice.exceptions;

import lombok.*;

@Getter
@Setter
public class ExceptionMessage{

    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

}