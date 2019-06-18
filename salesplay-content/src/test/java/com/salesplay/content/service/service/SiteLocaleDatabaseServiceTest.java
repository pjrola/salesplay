package com.salesplay.content.service.service;

import com.salesplay.content.service.domain.SiteLocale;
import com.salesplay.content.service.exception.DuplicateResourceException;
import com.salesplay.content.service.exception.ResourceNotFoundException;
import com.salesplay.content.service.repository.SiteLocaleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SiteLocaleDatabaseServiceTest {

    @Mock
    private SiteLocaleRepository repository;

    @InjectMocks
    private SiteLocaleDatabaseService service;

    private SiteLocale siteLocale;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void canRetrievePageList() {

    }

    @Test
    public void canFindById() throws Exception {

    }

    @Test(expected = ResourceNotFoundException.class)
    public void throwsResourceNotFoundExceptionWhenFindByIdNotFound() throws Exception {

    }

    @Test
    public void canDeleteById() throws Exception {

    }

    @Test
    public void canDeleteByEntity() throws Exception {

    }

    @Test
    public void canSaveAllEntities() {

    }

    @Test
    public void canSaveEntity() throws Exception {

    }

    @Test(expected = DuplicateResourceException.class)
    public void throwsDuplicateResourceExceptionWhenSavingDuplicateEntity() throws Exception {

    }

    @Test
    public void canFindAllById() {

    }

    @Test
    public void canDeleteAll() throws Exception {

    }

    @Test
    public void canDeleteAllByEntities() throws Exception {

    }

}
