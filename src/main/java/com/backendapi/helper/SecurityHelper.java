package com.backendapi.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SecurityHelper {

    private static final String salt = "mysalt";

    public static String hash(String str) {
        String hashedStr = get_SHA_256_SecurePassword(str, salt);
        System.out.println(hashedStr);
        return hashedStr;
    }

    public static boolean compare(String str, String hash) {
        String hashedStr = get_SHA_256_SecurePassword(str, salt);
        return hashedStr == hash;
    }

    private static String get_SHA_256_SecurePassword(String passwordToHash,
                                              String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
