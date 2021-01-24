package com.campool.utils;

import java.security.MessageDigest;

public class SHA256Utility {

    // 인스턴스화 방지
    private SHA256Utility() {
        // 내부에서 사용 시 에러를 통해 방지
        throw new AssertionError();
    }

    public static String encrypt(String msg) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] passBytes = msg.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuilder sb = new StringBuilder();
            for (byte b : digested) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
