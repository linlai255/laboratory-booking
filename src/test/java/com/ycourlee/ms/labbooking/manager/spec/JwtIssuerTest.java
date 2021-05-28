package com.ycourlee.ms.labbooking.manager.spec;

import com.ycourlee.ms.labbooking.LabBookingApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yongjiang
 */
public class JwtIssuerTest extends LabBookingApplicationTests {

    @Autowired
    private JwtIssuer jwtIssuer;

    @Test
    public void issueTest() {
        String hello = jwtIssuer.issue("i am fine.");
        System.out.println("hello = " + hello);

        System.out.println("jwtIssuer.claimsOf(hello) = " + jwtIssuer.claimsOf(hello));

        System.out.println("jwtIssuer.parse(hello) = " + jwtIssuer.parse(hello));
    }
}
