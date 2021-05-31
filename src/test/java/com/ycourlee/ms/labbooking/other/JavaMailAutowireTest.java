package com.ycourlee.ms.labbooking.other;

import com.ycourlee.ms.labbooking.LabBookingApplicationTests;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * @author yongjiang
 */
public class JavaMailAutowireTest extends LabBookingApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(JavaMailAutowireTest.class);

    @Autowired
    private MailSender mailSender;

    @Test
    void send2Test() {
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setTo("68261772@qq.com");
        simpleMessage.setFrom("ycourlee@qq.com");
        simpleMessage.setText("hello");
        mailSender.send(simpleMessage);
    }
}
