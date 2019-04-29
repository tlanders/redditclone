package biz.lci.springboot.redditclone.service;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final ResourceBundleMessageSource resourceBundleMessageSource;

    public MessageService(ResourceBundleMessageSource resourceBundleMessageSource) {
        this.resourceBundleMessageSource = resourceBundleMessageSource;
    }

    public String getMessage(String name, String defaultValue) {
        return resourceBundleMessageSource.getMessage(name, null, defaultValue, LocaleContextHolder.getLocale());
    }
}
