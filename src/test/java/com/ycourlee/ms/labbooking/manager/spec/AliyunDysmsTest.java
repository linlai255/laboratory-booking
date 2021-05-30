package com.ycourlee.ms.labbooking.manager.spec;

import com.ycourlee.ms.labbooking.LabBookingApplicationTests;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yongjiang
 */
public class AliyunDysmsTest extends LabBookingApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(AliyunDysmsTest.class);

    @Autowired
    private AliyunDysms aliyunDysms;

    /**
     * failed.
     */
    @Test
    public void sendVerifyCodeWhenRegisterTest() {
        String code = aliyunDysms.sendVerifyCodeWhenRegister("13115611802");
        log.info("code = {}", code);
    }
}
