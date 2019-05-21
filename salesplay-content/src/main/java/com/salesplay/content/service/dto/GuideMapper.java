package com.salesplay.content.service.dto;

import com.salesplay.content.service.domain.Guide;
import com.salesplay.content.service.domain.GuideTranslation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import java.util.List;

@Mapper(componentModel="spring")
@Component
public interface GuideMapper {

    GuideMapper INSTANCE = Mappers.getMapper(GuideMapper.class);

    @Mappings({
            @Mapping(source = "translation.title", target = "title"),
            @Mapping(source = "translation.locale.code", target = "locale"),
            @Mapping(source = "translation.subtitle", target = "subtitle"),
            @Mapping(source = "translation.overview", target = "overview"),
            @Mapping(source = "guide.slug", target = "slug"),
            @Mapping(source = "guide.editorialStatus", target = "editorialStatus"),
            @Mapping(source = "guide.visibility", target = "visibility"),
            @Mapping(source = "guide.image", target = "image")
    })
    GuideDTO guideToGuideDto(Guide guide, GuideTranslation translation);

    List<GuideDTO> toGuideDTOs(List<Guide> guides);
}