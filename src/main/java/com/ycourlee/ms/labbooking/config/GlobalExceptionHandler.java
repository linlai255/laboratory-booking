package com.ycourlee.ms.labbooking.config;

import com.ycourlee.ms.labbooking.exception.AuthenticationException;
import com.ycourlee.ms.labbooking.exception.error.Errors;
import com.ycourlee.root.core.domain.context.Rtm;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author yongjiang
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AuthenticationException.class)
    public Rtm authenticationException(AuthenticationException exception) {
        return Rtm.error(exception.getCmReturn());
    }

    @ExceptionHandler(value = Exception.class)
    public Rtm exception(Exception exception) {
        return Rtm.error(Errors.UNKNOWN.getCode(), Errors.UNKNOWN.getMsg(), exception.getMessage());
    }
}
