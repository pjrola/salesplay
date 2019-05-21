package com.salesplay.content.service.domain;

import com.google.common.collect.ImmutableList;
import lombok.Getter;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import static com.google.common.base.Preconditions.checkNotNull;

@Entity
@ToString
@Getter
@Table(name = "guides")
public class Guide extends PersistentObject {

    @NotEmpty(message = "{guide.slug.notNull}")
    @Column(name = "slug", nullable = false)
    private String slug;

    @Convert(converter = EditorialStatus.Converter.class)
    @Column(name = "editorial_status", nullable = false)
    private EditorialStatus editorialStatus = EditorialStatus.DRAFT;

    @Column(name = "visibility", nullable = false)
    @Convert(converter = Visibility.Converter.class)
    private Visibility visibility = Visibility.PUBLIC;

    @NotBlank(message = "{guide.image.notNull}")
    @Size(min = 5, max = 200, message = "{guide.image.notNull}")
    @Column(name = "image", nullable = false)
    private String image;

    @ElementCollection
    @Valid
    @Size(min = 1, message = "missing translations")
    @CollectionTable(name="guide_translations", joinColumns=@JoinColumn(name="guide_id"))
    private List<GuideTranslation> translations = new ArrayList<>();

    protected Guide(){}

    private Guide(String slug, EditorialStatus editorialStatus, Visibility visibility, String image) {
        this.slug = slug;
        this.editorialStatus = editorialStatus;
        this.visibility = visibility;
        this.image = image;
    }

    public static Guide of(String slug, EditorialStatus editorialStatus, Visibility visibility, String image) {
        checkNotNull(slug);
        checkNotNull(editorialStatus);
        checkNotNull(visibility);
        checkNotNull(image);
        return new Guide(slug, editorialStatus, visibility, image);
    }

    public List<GuideTranslation> getTranslations() {
        return ImmutableList.copyOf(translations);
    }

    public void addTranslation(GuideTranslation translation) {
        checkNotNull(translation);

        if (!translations.contains(translation)) {
            translations.add(translation);
        } else {
            throw new IllegalArgumentException("duplicate locale in json");
        }
    }

    public void addTranslations(List<GuideTranslation> translations) {
        checkNotNull(translations);
        for (GuideTranslation translation : translations) {
            addTranslation(translation);
        }
    }

    public void removeTranslation(GuideTranslation translation) {
        checkNotNull(translation);
        translations.remove(translation);
    }

    public void removeTranslations(List<GuideTranslation> translations) {
        checkNotNull(translations);
        for (GuideTranslation translation : translations) {
            removeTranslation(translation);
        }
    }

    public GuideTranslation getTranslationByLocale(SiteLocale locale) {
        checkNotNull(locale);
        for(GuideTranslation translation : translations) {
            if(translation.getLocale().getCode().equals(locale.getCode())) {
                return translation;
            }
        }
        return null;
    }

}
