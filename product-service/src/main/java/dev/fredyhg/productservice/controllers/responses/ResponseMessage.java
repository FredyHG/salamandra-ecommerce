package dev.fredyhg.productservice.controllers.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseMessage {
    private int code;
    private String message;
}
