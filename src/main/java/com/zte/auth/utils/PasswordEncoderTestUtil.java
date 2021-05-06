package com.zte.auth.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 密码解析器测试
 */
public class PasswordEncoderTestUtil {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String rawPassword = "123456";
        String encodePassword = passwordEncoder.encode(rawPassword);
        System.out.println("原始密码：" + rawPassword);
        System.out.println("加密以后的密码:"+ encodePassword);

        System.out.println(rawPassword + "是否匹配" + encodePassword + ":" + passwordEncoder.matches(rawPassword,encodePassword));
        System.out.println("654321是否匹配" + encodePassword + ":" + passwordEncoder.matches("654321",encodePassword));

    }
}
