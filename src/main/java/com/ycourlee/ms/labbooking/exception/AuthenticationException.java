package com.ycourlee.ms.labbooking.exception;

import com.ycourlee.ms.labbooking.exception.error.Errors;
import com.ycourlee.root.core.context.BusinessException;
import com.ycourlee.root.core.context.CmReturn;

/**
 * @author yongjiang
 */
public class AuthenticationException extends BusinessException {

    public AuthenticationException() {
        super(Errors.CANNOT_AUTHENTICATE);
    }

    public AuthenticationException(CmReturn cmReturn) {
        super(cmReturn);
    }
}
