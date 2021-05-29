package com.ycourlee.ms.labbooking.config.properties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.convert.DurationUnit;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

/**
 * @author yongjiang
 */
@Setter
@Getter
public class LabJwtProperties {

    private static final Logger log = LoggerFactory.getLogger(LabJwtProperties.class);

    public static final String PREFIX = "lab-app.jwt";

    /**
     * subject of issuing jwt.
     */
    private String subject   = "Lab booking system token";
    /**
     * jwt issuer.
     */
    private String issuer    = "Lab booking system";
    /**
     * sign secret key.
     */
    private String secretKey = "iam fine";

    @Setter(AccessLevel.NONE)
    private byte[] base64EncodedSecretKey = Base64.getEncoder().encode(secretKey.getBytes(StandardCharsets.UTF_8));

    /**
     * the only one custom claim key.
     */
    private String   claimName       = "HowAreYou";
    /**
     * prefix symbol.
     */
    private String   leadingSymbol   = "bearer";
    /**
     * default expiration time in seconds.
     */
    @DurationUnit(ChronoUnit.SECONDS)
    private Duration defaultDuration = Duration.ofSeconds(60L * 60 * 3 + 60);

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
        this.base64EncodedSecretKey = Base64.getEncoder().encode(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String getSecretKey() {
        if (log.isWarnEnabled()) {
            log.warn("Unsafe access to secret key.");
        }
        return secretKey;
    }
}
