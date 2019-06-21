package com.cloud.service.service;

public interface MessageByLocaleService {
    public String getMessage(String code);
    String getMessageWithParam(String code, Object[] var2);
}
