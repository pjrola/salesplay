package com.salesplay.content.service.service;

import com.salesplay.content.service.domain.*;
import com.salesplay.content.service.exception.DuplicateResourceException;
import com.salesplay.content.service.exception.ResourceNotFoundException;
import com.salesplay.content.service.repository.MessageResourceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class MessageByLocaleDatabaseServiceTest {

    @Mock
    private MessageResourceRepository resourceRepository;

    @InjectMocks
    private MessageByLocaleDatabaseService service;

    private List<MessageResource> resources = new ArrayList<>();

    private MessageResource messageResourceMock;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        messageResourceMock = MessageResource.of("en", "key", "content", true, false);

        resources.add(messageResourceMock);
        resources.add(messageResourceMock);
        resources.add(messageResourceMock);
    }

    @Test
    public void canRetrievePageList() {
        Page<MessageResource> resourcePage = new PageImpl<>(resources);

        when(resourceRepository.findAll(PageRequest.of(0, 10))).thenReturn(resourcePage);

        Page<MessageResource> result = service.findAll(PageRequest.of(0, 10));
        assertTrue(result.hasContent());
        assertEquals(3, result.getTotalElements());
    }

    @Test
    public void canFindById() throws Exception {
        when(resourceRepository.findById(1L)).thenReturn(Optional.ofNullable(messageResourceMock));
        Optional<MessageResource> result = Optional.ofNullable(service.findById(1L));
        assertTrue(result.isPresent());
        assertEquals(result.get(), messageResourceMock);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void throwsResourceNotFoundExceptionWhenFindByIdNotFound() throws Exception {
        when(service.findById(1L)).thenThrow(new ResourceNotFoundException("1L"));
    }

    @Test
    public void canGetMessageByCode() throws Exception {

    }

    @Test
    public void canGetMessageWithParamCode() throws Exception {

    }

    @Test
    public void canSaveAllEntities() throws Exception {
        when(resourceRepository.saveAll(resources)).thenReturn(resources);
        Iterable<MessageResource> result = service.saveAll(resources);

        assertEquals(resources, result);
        verify(resourceRepository, times(1)).saveAll(resources);
    }

    @Test(expected = DuplicateResourceException.class)
    public void throwsDuplicateResourceExceptionWhenSavingDuplicateEntity() throws Exception {
        when(resourceRepository.existsByKeyAndLocale(messageResourceMock.getKey(), messageResourceMock.getLocale())).thenReturn(true);
        when(service.create(messageResourceMock)).thenThrow(new DuplicateResourceException(messageResourceMock.getKey()));
        verify(resourceRepository, times(0)).save(messageResourceMock);
    }

    @Test
    public void canSaveEntity() throws Exception {
        when(resourceRepository.save(messageResourceMock)).thenReturn(messageResourceMock);
        MessageResource result = service.create(messageResourceMock);
        assertEquals(messageResourceMock, result);
        verify(resourceRepository, times(1)).save(messageResourceMock);
    }

    @Test
    public void canUpdateEntity() throws Exception {

    }



}

