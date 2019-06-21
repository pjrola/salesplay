package com.cloud.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import java.util.Locale;

@Component
public class MessageByLocaleServiceImpl implements MessageByLocaleService {

    @Autowired
    private MessageSource messageSource;

    @Override
    public String getMessage(String code) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code,null, locale);
    }

    public String getMessageWithParam(String code, Object[] var2) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, var2, locale);
    }
}
