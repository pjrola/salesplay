package com.salesplay.content.service.dto;

import com.salesplay.content.service.domain.EditorialStatus;
import com.salesplay.content.service.domain.Visibility;
import lombok.*;
import javax.validation.constraints.NotBlank;
import static com.google.common.base.Preconditions.checkNotNull;

@Getter
@Setter
@ToString
public class GuideDTO {

    @NotBlank(message = "{guide.image.notNull}")
    private String locale;

    @NotBlank(message = "{guide.title.notNull}")
    private String title;

    @NotBlank(message = "{guide.subtitle.notNull}")
    private String subtitle;

    @NotBlank(message = "{guide.slug.notNull}")
    private String slug;

    private EditorialStatus editorialStatus = EditorialStatus.DRAFT;

    private Visibility visibility = Visibility.PUBLIC;

    @NotBlank(message = "{guide.image.notNull}")
    private String image;

    @NotBlank(message = "{guide.overview.notNull}")
    private String overview;

    protected GuideDTO(){}

    public GuideDTO(String locale, String title, String subtitle, String slug, EditorialStatus editorialStatus, Visibility visibility, String image, String overview) {
        this.locale = locale;
        this.title = title;
        this.subtitle = subtitle;
        this.slug = slug;
        this.editorialStatus = editorialStatus;
        this.visibility = visibility;
        this.image = image;
        this.overview = overview;
    }

    public static GuideDTO of(String locale, String title, String subtitle, String slug, EditorialStatus editorialStatus, Visibility visibility, String image, String overview) {
        checkNotNull(locale);
        checkNotNull(title);
        checkNotNull(subtitle);
        checkNotNull(slug);
        checkNotNull(image);
        checkNotNull(overview);
        return new GuideDTO(locale, title, subtitle, slug, editorialStatus, visibility, image, overview);
    }
}
