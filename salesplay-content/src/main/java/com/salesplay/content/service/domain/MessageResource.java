package com.salesplay.content.service.domain;

import lombok.Getter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import static com.google.common.base.Preconditions.checkNotNull;

@Entity
@Getter
@Table(name = "resource_messages")
public class MessageResource extends PersistentObject {

    @NotBlank(message = "{translation.slug.notNull}")
    @Column(name = "locale", nullable = false)
    private String locale;

    @NotBlank(message = "{translation.key.notNull}")
    @Column(name = "messagekey", nullable = false, unique = true)
    private String key;

    @NotBlank(message = "{translation.content.notNull}")
    @Column(name = "messagecontent", nullable = false)
    private String content;

    @Column(name = "verified")
    private Boolean verified;

    @Column(name = "excluded")
    private Boolean excluded;

    protected MessageResource(){}

    public MessageResource(String locale, String key, String content, Boolean verified, Boolean excluded) {
        this.locale = locale;
        this.key = key;
        this.content = content;
        this.verified = verified;
        this.excluded = excluded;
    }

    public static MessageResource of(String locale, String key, String content, Boolean verified, Boolean excluded) {
        checkNotNull(locale);
        checkNotNull(key);
        checkNotNull(content);
        checkNotNull(verified);
        checkNotNull(excluded);
        return new MessageResource(locale, key, content, verified, excluded);
    }
}