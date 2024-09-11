package dev.fredyhg.customerservice.controller.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
public record ResponseMessage(Integer code, String message, LocalDateTime timestamp) {
}
