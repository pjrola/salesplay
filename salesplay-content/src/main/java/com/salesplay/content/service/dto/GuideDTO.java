package com.salesplay.content.service.dto;

import com.salesplay.content.service.domain.EditorialStatus;
import com.salesplay.content.service.domain.Visibility;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GuideDTO {
    private String locale;
    private String title;
    private String subtitle;
    private String slug;
    private EditorialStatus editorialStatus;
    private Visibility visibility;
    private String image;
    private String overview;
}
