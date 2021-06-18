package com.ycourlee.ms.labbooking.config;

import com.alibaba.fastjson.JSON;
import com.ycourlee.ms.labbooking.exception.error.Errors;
import com.ycourlee.root.core.context.BusinessException;
import com.ycourlee.root.core.domain.context.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yongjiang
 */
@RestControllerAdvice
@SuppressWarnings("rawtypes")
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = BusinessException.class)
    public ApiResponse businessException(BusinessException e) {
        e.printStackTrace();
        return ApiResponse.error(e.getCmReturn());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    public ApiResponse bindExceptionHandler(HttpServletRequest request, Exception e) {
        // multipart request will be logged.
        log.error("Failed to process the request, uri: {}, parameter map: {}", request.getRequestURI(), JSON.toJSON(request.getParameterMap()));
        e.printStackTrace();
        BindingResult bindingResult;
        if (e instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
        } else {
            bindingResult = ((BindException) e);
        }

        StringBuffer sb = new StringBuffer();
        bindingResult.getFieldErrors().forEach(fieldError ->
                sb.append(fieldError.getField())
                        .append("-")
                        .append(fieldError.getDefaultMessage())
                        .append(";"));
        return ApiResponse.error(Errors.REQUEST_PARAMETER_VALIDATION_ERROR.getCode(),
                Errors.REQUEST_PARAMETER_VALIDATION_ERROR.getMsg() + " " + sb.toString(),
                null);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = Exception.class)
    public ApiResponse exception(Exception e) {
        log.error("exception message: {}", e.getMessage());
        e.printStackTrace();
        // notice: null pointer exception's message is null. so e.getMessage() will be null.
        return ApiResponse.error(Errors.UNKNOWN.getCode(),
                Errors.UNKNOWN.getMsg(),
                null);
    }
}
