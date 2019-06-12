package com.salesplay.content.service.controller;

import com.salesplay.content.service.domain.Guide;
import com.salesplay.content.service.dto.GuideDTO;
import com.salesplay.content.service.dto.GuideMapper;
import com.salesplay.content.service.exception.DuplicateResourceException;
import com.salesplay.content.service.exception.ResourceNotFoundException;
import com.salesplay.content.service.service.GuideService;
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
public class GuideController implements CrudController<GuideDTO> {

    private static final String RESOURCE_PATH = "/guides";

    @NotNull
    private GuideService service;

    private GuideMapper mapper;

    @Autowired
    public GuideController(GuideService service, GuideMapper mapper) {
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
    public GuideDTO create(@Valid @RequestBody GuideDTO dto) throws DuplicateResourceException {
        Guide guide = mapper.mapFromDto(dto);
        service.create(guide);
        return mapper.mapToDto(guide);
    }

    @PostMapping(RESOURCE_PATH + "/delete")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestBody List<Guide> guides) {
        service.deleteAll(guides);
    }

    @PutMapping(RESOURCE_PATH)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Guide update(@Valid @RequestBody Guide guide) throws ResourceNotFoundException {
        return service.update(guide);
    }

    @GetMapping(RESOURCE_PATH + "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GuideDTO findById(@PathVariable(value = "id") Long guideId) throws ResourceNotFoundException {
        return mapper.mapToDto(service.findById(guideId));
    }

    @DeleteMapping(RESOURCE_PATH + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void deleteById(Long id) throws ResourceNotFoundException {
        service.deleteById(id);
    }

    @Override
    public GuideDTO update(GuideDTO entity) throws ResourceNotFoundException {
        return null;
    }

    @GetMapping(RESOURCE_PATH + "/slug/{slug}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Guide findBySlug(@PathVariable(value = "slug") String slug) throws ResourceNotFoundException {
        return service.findBySlug(slug);
    }
}