package com.ycourlee.ms.labbooking.manager.spec;

import com.ycourlee.ms.labbooking.LabBookingApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yongjiang
 */
public class VerifyCodeSenderTest extends LabBookingApplicationTests {

    @Autowired
    private VerifyCodeSender verifyCodeSender;

    @Test
    void aTest() {
        verifyCodeSender.sendEmailVerifyCodeWhenRegistration("68261772@qq.com");
    }
}
