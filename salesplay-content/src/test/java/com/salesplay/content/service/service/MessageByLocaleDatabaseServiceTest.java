package com.salesplay.content.service.service;

import com.salesplay.content.service.domain.*;
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
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MessageByLocaleDatabaseServiceTest {

    @Mock
    private MessageResourceRepository resourceRepository;

    @InjectMocks
    private MessageByLocaleDatabaseService service;

    private List<MessageResource> resources = new ArrayList<>();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MessageResource messageResourceMock = MessageResource.of("en", "key", "content", true, false);

        resources.add(messageResourceMock);
        resources.add(messageResourceMock);
        resources.add(messageResourceMock);
    }

    @Test
    public void canRetrievePageList() throws Exception {
        Page<MessageResource> resourcePage = new PageImpl<>(resources);

        when(resourceRepository.findAll(PageRequest.of(0, 10))).thenReturn(resourcePage);

        Page<MessageResource> result = service.findAll(PageRequest.of(0, 10));
        assertTrue(result.hasContent());
        assertEquals(3, result.getTotalElements());
    }




}

