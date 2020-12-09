package com.demo.util;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.util.Random;

public class Md5SaltUtils {
    private static final int STEP = 3;
    private static final int CAPACITY = 48;

    /**
     * <p>
     * 生成含有随机盐的密码
     * </p>
     *
     * @param password
     * @return
     */
    public static String generate(String password) {
        String salt = getSalt(16);

        password = md5Hex(password + salt);
        char[] cs = new char[CAPACITY];
        for (int i = 0; i < CAPACITY; i += STEP) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return new String(cs);
    }

    /**
     * <p>
     * 生成含有随机盐的密码
     * </p>
     *
     * @param password
     * @param salt
     * @return
     */
    public static String generateBySalt(String password, String salt) {

        password = md5Hex(password + salt);
        char[] cs = new char[CAPACITY];
        for (int i = 0; i < CAPACITY; i += STEP) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return new String(cs);
    }

    /**
     * <p>
     * 生成随机盐（一般大于等于16位）
     * </p>
     *
     * @param len
     * @return
     */
    public static String getSalt(int len) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder(len);
        sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
        int length = sb.length();
        if (length < len) {
            for (int i = 0; i < len - length; i++) {
                sb.append("0");
            }
        }
        String salt = sb.toString();
        return salt;
    }

    /**
     * <p>
     * 校验密码是否正确
     * </p>
     *
     * @param password
     * @param md5
     * @return
     */
    public static boolean verify(String password, String md5) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < md5.length(); i += STEP) {
            cs1[i / 3 * 2] = md5.charAt(i);
            cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
            cs2[i / 3] = md5.charAt(i + 1);
        }
        String salt = new String(cs2);
        return md5Hex(password + salt).equals(new String(cs1));
    }

    /**
     * <p>
     * 获取十六进制字符串形式的MD5
     * </p>
     *
     * @param src
     * @return
     */
    public static String md5Hex(String src) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = md5.digest(src.getBytes());
            return new String(new Hex().encode(bs));
        } catch (Exception e) {
            return null;
        }
    }
}
