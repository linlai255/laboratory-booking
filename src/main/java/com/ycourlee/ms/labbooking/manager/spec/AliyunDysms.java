package com.ycourlee.ms.labbooking.manager.spec;

import com.alibaba.fastjson.JSON;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.aliyun.teaopenapi.models.Config;
import com.ycourlee.ms.labbooking.config.properties.AliyunDysmsProperties;
import com.ycourlee.ms.labbooking.exception.error.Errors;
import com.ycourlee.root.core.context.BusinessException;
import com.ycourlee.root.util.Assert;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * aliyun dysms application was denied. Deprecated the way of registration.
 *
 * @author yongjiang
 */
@Deprecated
@Component
public class AliyunDysms {

    private static final Logger                log = LoggerFactory.getLogger(AliyunDysms.class);
    @Getter
    private              AliyunDysmsProperties properties;
    private              Client                clientSingleton;

    public AliyunDysms(AliyunDysmsProperties properties) {
        this.properties = properties;
    }

    public String sendVerifyCodeWhenRegister(String phone) {
        try {
            initClient();
            return sendSms(phone);
        } catch (Exception e) {
            throw new BusinessException(Errors.SMS_SEND_FAILED);
        }
    }

    private String sendSms(String phone) throws Exception {
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phone);
        SendSmsResponseBody body = clientSingleton.sendSms(sendSmsRequest).getBody();
        log.info("JSON.toJSONString(body) = {}", JSON.toJSONString(body));
        if (body == null || !"OK".equals(body.getCode())) {
            log.error("sms service error, message: {}", body == null ? "null" : body.getMessage());
            throw new BusinessException(Errors.SMS_SEND_FAILED);
        }
        if (log.isDebugEnabled()) {
            log.debug("phone: {}, sms response body: {}", phone, body.toMap());
        }
        return body.getCode();
    }

    private void initClient() throws Exception {
        if (clientSingleton == null) {
            synchronized (this) {
                if (clientSingleton == null) {
                    clientSingleton = createClient();
                }
            }
        }
    }

    private Client createClient() throws Exception {

        Assert.notEmpty(properties.getAccessKeyId());
        Assert.notEmpty(properties.getAccessKeySecret());
        Assert.notEmpty(properties.getEndpoint());

        log.info("here");

        Config config = new Config()
                .setAccessKeyId(properties.getAccessKeyId())
                .setAccessKeySecret(properties.getAccessKeySecret())
                .setEndpoint(properties.getEndpoint());
        return new Client(config);
    }
}
