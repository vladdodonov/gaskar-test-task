package com.test.demo.config;

import com.test.demo.dto.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@Configuration
@ControllerAdvice
public class ExceptionHandlerController {
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APP_JSON = "application/json";
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity handleEntityNotFoundException(EntityNotFoundException ex) {
        return prepareResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({Throwable.class})
    public ResponseEntity handleAllExceptions(Throwable ex) {
        return prepareResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity prepareResponse(Throwable e, HttpStatus status) {
        log.error(String.format("status: %s", status), e);
        HttpHeaders headers = new HttpHeaders();
        headers.add(CONTENT_TYPE, APP_JSON);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(e.getMessage());
        return new ResponseEntity(errorDto, headers, status);
    }
}
