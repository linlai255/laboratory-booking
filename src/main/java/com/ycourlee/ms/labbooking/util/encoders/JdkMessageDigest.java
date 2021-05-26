package com.ycourlee.ms.labbooking.util.encoders;

import com.ycourlee.root.util.StringUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author yongjiang
 */
public class JdkMessageDigest {

    public static String sha1(String plaintext) {
        if (StringUtil.isEmpty(plaintext)) {
            throw new IllegalArgumentException();
        }
        try {
            return encode(plaintext, MessageDigest.getInstance("MD5"));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("cannot get 'SHA' instance.");
        }
    }

    public static String md5(String plaintext) {
        if (StringUtil.isEmpty(plaintext)) {
            throw new IllegalArgumentException();
        }
        try {
            return encode(plaintext, MessageDigest.getInstance("MD5"));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("cannot get 'MD5' instance.");
        }
    }

    private static String encode(String plaintext, MessageDigest messageDigest) {
        return Hex.encode(messageDigest.digest(plaintext.getBytes()));
    }
}
