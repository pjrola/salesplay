package com.salesplay.content.service.controller;

import com.salesplay.content.service.domain.Guide;
import com.salesplay.content.service.dto.GuideDTO;
import com.salesplay.content.service.dto.GuideMapper;
import com.salesplay.content.service.service.GuideDatabaseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RestController
public class GuideController {

    private static final String RESOURCE_PATH = "/guides";

    @NotNull
    private GuideDatabaseService service;
    private GuideMapper mapper;

    @Autowired
    public GuideController(GuideDatabaseService service, GuideMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(RESOURCE_PATH)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Page<GuideDTO> findAll(Pageable pageable) {
        Page<Guide> guidePage = service.findAll(pageable);

        List<GuideDTO> guideDTOS = guidePage.getContent()
                .stream()
                .map(guide -> mapper.mapToDto(guide))
                .collect(Collectors.toList());

        return new PageImpl<>(guideDTOS, pageable, guidePage.getTotalPages());
    }

    @PostMapping(RESOURCE_PATH)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public GuideDTO create(@Valid @RequestBody GuideDTO dto) {
        return null;
    }
}