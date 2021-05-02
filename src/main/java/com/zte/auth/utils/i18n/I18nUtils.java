package com.zte.auth.utils.i18n;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * 国际化工具类
 */
@Component
@Slf4j
public class I18nUtils {
    private static MessageSource messageSource;

    public I18nUtils(MessageSource messageSource) {
        I18nUtils.messageSource = messageSource;
    }

    /**
     * 获取单个国际化翻译值
     */
    public static String get(String msgKey) {
        try {
            return messageSource.getMessage(msgKey, null, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            log.error(e.getMessage());
            return msgKey;
        }
    }

    /**
     * 获取单个国际化翻译值
     * get("key","123","234")
     * key=参数{0},参数{1}
     */
    public static String get(String msgKey,String... params) {
        try {
            return messageSource.getMessage(msgKey, params, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            log.error(e.getMessage());
            return msgKey;
        }
    }
}
