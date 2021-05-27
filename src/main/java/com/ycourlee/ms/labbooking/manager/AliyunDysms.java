package com.ycourlee.ms.labbooking.manager;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yongjiang
 */
@Component
public class AliyunDysms {

    private static final Logger                log = LoggerFactory.getLogger(AliyunDysms.class);
    @Getter
    @Autowired
    private              AliyunDysmsProperties properties;
    private              Client                clientSingleton;

    public AliyunDysms(AliyunDysmsProperties properties) {
        this.properties = properties;
    }

    public String sendCacheVerifyCodeWhenRegister(String phone) {
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

        Assert.isNotEmpty(properties.getAccessKeyId());
        Assert.isNotEmpty(properties.getAccessKeySecret());
        Assert.isNotEmpty(properties.getEndpoint());

        Config config = new Config()
                .setAccessKeyId(properties.getAccessKeyId())
                .setAccessKeyId(properties.getAccessKeySecret())
                .setEndpoint(properties.getEndpoint());
        return new Client(config);
    }
}
