package com.ycourlee.ms.labbooking.exception;

import com.ycourlee.ms.labbooking.exception.error.Errors;
import com.ycourlee.root.core.context.BusinessException;
import com.ycourlee.root.core.context.CmReturn;

/**
 * @author yongjiang
 */
public class AuthorizationException extends BusinessException {

    public AuthorizationException() {
        super(Errors.CANNOT_AUTHORIZATION);
    }

    public AuthorizationException(CmReturn cmReturn) {
        super(cmReturn);
    }
}
