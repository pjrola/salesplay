package com.salesplay.content.service.config;

import com.salesplay.content.service.domain.MessageResource;
import com.salesplay.content.service.repository.MessageResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotNull;
import java.text.MessageFormat;
import java.util.Locale;

@Component("messageSource")
public class DBMessageResource extends AbstractMessageSource {

    private static final String DEFAULT_LOCALE_CODE = "en";

    @NotNull
    private MessageResourceRepository repository;

    @Autowired
    public DBMessageResource(MessageResourceRepository repository) {
        this.repository = repository;
    }

    @Override
    protected MessageFormat resolveCode(String key, Locale locale) {
        MessageResource message = repository.findByKeyAndLocale(key,locale.getLanguage());
        if (message == null) {
            message = repository.findByKeyAndLocale(key, DEFAULT_LOCALE_CODE);
        }
        return new MessageFormat(message.getContent(), locale);
    }
}