package com.salesplay.content.service.domain;

import lombok.Getter;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;
import static com.google.common.base.Preconditions.checkNotNull;

@Getter
@Embeddable
public class GuideTranslation implements Serializable {

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "locale_id", referencedColumnName="id")
    private SiteLocale locale;

    @NotEmpty(message = "{guide.title.notNull}")
    @Column(name = "title", nullable = false)
    private String title;

    @NotEmpty(message = "{guide.subtitle.notNull}")
    @Column(name = "subtitle", nullable = false)
    private String subtitle;

    @NotEmpty(message = "{guide.overview.notNull}")
    @Column(name = "overview", columnDefinition="TEXT", nullable = false)
    private String overview;

    private GuideTranslation(){}

    private GuideTranslation(SiteLocale locale, String title, String subtitle, String overview) {
        this.locale = locale;
        this.title = title;
        this.subtitle = subtitle;
        this.overview = overview;
    }

    public static GuideTranslation of(SiteLocale locale, String title, String subtitle, String overview) {
        checkNotNull(locale);
        checkNotNull(title);
        checkNotNull(subtitle);
        checkNotNull(overview);
        return new GuideTranslation(locale, title, subtitle, overview);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GuideTranslation)) return false;
        GuideTranslation that = (GuideTranslation) o;
        return locale.equals(that.locale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locale);
    }
}