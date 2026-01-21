package com.codeline.CertiGo.Exceptions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private final String message;
    private final String error;
    private final Date timeStamp;
    private final HttpStatus status;

}
