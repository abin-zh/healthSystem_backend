package com.cykj.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5摘要算法工具类
 * @author abin
 * @date 2024/6/26 14:12
 */
public class MD5Utils {
    private static String backSalt = "";
    private static String frontSalt = "";

    public static String encrypt(String context){
        String hashDigest = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            String newContext = frontSalt + context + backSalt;
            byte[] digest = messageDigest.digest(newContext.getBytes());
            StringBuilder builder = new StringBuilder();
            for (byte b : digest) {
                builder.append(String.format("%02x",b));
            }
            hashDigest = builder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return hashDigest;
    }
}
