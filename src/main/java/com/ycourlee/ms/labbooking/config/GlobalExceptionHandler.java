package com.ycourlee.ms.labbooking.config;

import com.ycourlee.ms.labbooking.exception.AuthenticationException;
import com.ycourlee.ms.labbooking.exception.error.Errors;
import com.ycourlee.root.core.domain.context.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.net.BindException;

/**
 * @author yongjiang
 */
@RestControllerAdvice
@SuppressWarnings("rawtypes")
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = AuthenticationException.class)
    public ApiResponse authenticationException(AuthenticationException exception) {
        return ApiResponse.error(exception.getCmReturn());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = BindException.class)
    public ApiResponse bindExceptionHandler(HttpServletRequest request, BindingResult e) {
        log.error("Failed to process the request, uri = {}", request.getRequestURI());
        StringBuffer sb = new StringBuffer();
        e.getFieldErrors().forEach(fieldError ->
                sb.append(fieldError.getField())
                        .append("=")
                        .append(fieldError.getDefaultMessage())
                        .append(";"));
        return ApiResponse.error(-1, sb.toString(), null);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = Exception.class)
    public ApiResponse exception(Exception exception) {
        return ApiResponse.error(Errors.UNKNOWN.getCode(), Errors.UNKNOWN.getMsg(), exception.getMessage());
    }
}
