package com.ycourlee.ms.labbooking.manager.spec;

import com.ycourlee.ms.labbooking.config.properties.LabJwtProperties;
import com.ycourlee.root.util.Assert;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

/**
 * only one claim in String style with {@linkplain LabJwtProperties#getClaimName() name}
 *
 * @author yongjiang
 */
@Component
public class JwtIssuer {

    private static final Logger log = LoggerFactory.getLogger(JwtIssuer.class);

    @Autowired
    private LabJwtProperties properties;

    public String issue(int durationInSecond, String claimValue) {
        return wrapJwt(baseJwt()
                .claim(properties.getClaimName(), claimValue)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + Duration.ofSeconds(durationInSecond).toMillis()))
                .compact());
    }

    public String issue(Object claimValue) {
        return wrapJwt(baseJwt()
                .claim(properties.getClaimName(), claimValue)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + properties.getDefaultDuration().toMillis()))
                .compact());
    }

    public String parse(String jwt) {
        return ((String) claimsOf(jwt).get(properties.getClaimName()));
    }

    public boolean check(String jwt) {
        try {
            Claims claims = claimsOf(jwt);
            Assert.that(claims.getIssuer().equals(properties.getIssuer()), "[ jwt ] unknown issuer. it's: " + jwt);
            Assert.that(claims.getSubject().equals(properties.getSubject()), "[ jwt ] unknown subject. it's: " + jwt);
            return true;
        } catch (ExpiredJwtException e) {
            log.warn("[ jwt ] expired. it's: {}. e:{}.", jwt, e.getMessage());
            return false;
        } catch (Exception e) {
            log.error("[ jwt ] parse error. it's: {}. e: {}.", jwt, e.getMessage());
            return false;
        }
    }

    public Claims claimsOf(String jwt) {
        return Jwts.parser()
                .setSigningKey(properties.getBase64EncodedSecretKey())
                .parseClaimsJws(unwrapJwt(jwt)).getBody();
    }

    private String wrapJwt(String jwt) {
        if (log.isDebugEnabled()) {
            log.debug("[ jwt ] issued an jwt: {}", jwt);
        }
        return properties.getLeadingSymbol() == null ? jwt : properties.getLeadingSymbol() + jwt;
    }

    private String unwrapJwt(String jwt) {
        if (jwt == null || jwt.isEmpty()) {
            return "";
        }
        if (properties.getLeadingSymbol() == null || properties.getLeadingSymbol().isEmpty()) {
            return jwt;
        }
        if (!jwt.startsWith(properties.getLeadingSymbol())) {
            throw new IllegalStateException("jwt style does not meet expectations. [ " + jwt + " ]");
        }
        return jwt.substring(properties.getLeadingSymbol().length());
    }

    private JwtBuilder baseJwt() {
        return Jwts.builder()
                .setSubject(properties.getSubject())
                .setIssuer(properties.getIssuer())
                .signWith(SignatureAlgorithm.HS256, properties.getBase64EncodedSecretKey());
    }
}
