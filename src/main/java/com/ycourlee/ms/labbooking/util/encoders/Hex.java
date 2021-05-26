package com.ycourlee.ms.labbooking.util.encoders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author yongjiang
 */
public class Hex {

    private static final Logger log = LoggerFactory.getLogger(Hex.class);

    private static final char[] HEX = "0123456789abcdef".toCharArray();

    public static String encode(String plaintext) {
        return encode(plaintext.getBytes(StandardCharsets.UTF_8));
    }

    public static String encode(String plaintext, Charset charset) {
        return encode(plaintext.getBytes(charset));
    }

    public static String encode(byte[] plainBytes) {
        if (plainBytes == null || plainBytes.length == 0) {
            throw new IllegalArgumentException();
        }
        StringBuilder sb = new StringBuilder(plainBytes.length << 1);
        for (byte b : plainBytes) {
            sb.append(HEX[(b & 0xf0) >> 4]).append(HEX[(b & 0x0f)]);
        }
        return sb.toString();
    }
}
