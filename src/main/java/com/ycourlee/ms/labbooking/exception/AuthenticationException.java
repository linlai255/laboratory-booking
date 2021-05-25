package com.ycourlee.ms.labbooking.exception;

import com.ycourlee.ms.labbooking.exception.error.Errors;
import com.ycourlee.root.core.context.BusinessException;

/**
 * @author yongjiang
 */
public class AuthenticationException extends BusinessException {

    public AuthenticationException() {
        super(Errors.CANNOT_AUTHENTICATE);
    }
}
