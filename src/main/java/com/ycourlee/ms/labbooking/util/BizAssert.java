package com.ycourlee.ms.labbooking.util;

import com.ycourlee.ms.labbooking.exception.error.Errors;
import com.ycourlee.root.core.context.BusinessException;
import com.ycourlee.root.core.context.CmReturn;
import com.ycourlee.root.util.Assert;

/**
 * @author yongjiang
 */
public class BizAssert extends Assert {

    /**
     * @param expr  expr
     * @param error Errors, {@link Errors}
     * @throws BusinessException thrown when expr is false
     */
    public static void that(boolean expr, CmReturn error) {
        if (!expr) {
            throw new BusinessException(error);
        }
    }

    public static void impossible(boolean expr, CmReturn error) {
        if (expr) {
            throw new BusinessException(error);
        }
    }

    /**
     * @param obj   object.
     * @param error Errors, {@link Errors}
     */
    public static void isNull(Object obj, CmReturn error) {
        if (obj != null) {
            throw new BusinessException(error);
        }
    }
}
