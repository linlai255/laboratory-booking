package com.ycourlee.ms.labbooking.manager.spec;

import com.ycourlee.ms.labbooking.exception.error.Errors;
import com.ycourlee.ms.labbooking.util.KeyPool;
import com.ycourlee.root.core.context.BusinessException;
import com.ycourlee.root.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

/**
 * @author yongjiang
 */
@Component
public class VerifyCodeSender {

    public static final  long       VERIFY_CODE_TIMEOUT_IN_SECONDS        = 60 * 5;
    public static final  long       APPLY_VERIFY_CODE_INTERVAL_IN_SECONDS = 60;
    public static final  String     VERIFY_CODE_EMAIL_TEXT_FORMAT         = " 欢迎注册并使用【Lab-Booking】系统，您的注册验证码为：%s。该验证码5分钟内有效，请勿泄漏于他人。";
    private static final Logger     log                                   = LoggerFactory.getLogger(VerifyCodeSender.class);
    @Autowired
    private              MailSender mailSender;
    @Autowired
    private              Redis      redis;

    public String sendEmailVerifyCodeWhenRegistration(String email) {
        String code = String.valueOf(RandomUtil.RANDOM.nextInt(1000000));
        try {
            mailSender.send(buildRegistrationVerifyCode(email, code));
            redis.setEx(KeyPool.registerCodeApplyFrequencyLock(email), code, APPLY_VERIFY_CODE_INTERVAL_IN_SECONDS);
        } catch (Exception e) {
            log.error("registration verify code send error. e message: {}", e.getMessage());
            throw new BusinessException(Errors.EMAIL_SEND_FAILED);
        }
        return code;
    }

    private SimpleMailMessage buildRegistrationVerifyCode(String email, String code) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("ycourlee@qq.com");
        mailMessage.setSubject("Lab-booking");
        mailMessage.setTo(email);
        mailMessage.setCc(email);
        mailMessage.setText(String.format(VERIFY_CODE_EMAIL_TEXT_FORMAT, code));
        return mailMessage;
    }
}
