package com.zte.auth.utils;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class JasyptUtil {

    public static void main(String[] args) {
        // 默认加密/解密算法是 PBEWithMD5AndDES
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        //撒盐
        encryptor.setPassword("123");
        //加密
        String enCode = encryptor.encrypt("root");
        //加密
        String enCode1 = encryptor.encrypt("Zsq123$");
        //解密
        encryptor.decrypt(enCode);
        System.out.println(enCode);
        System.out.println(enCode1);
    }
}

