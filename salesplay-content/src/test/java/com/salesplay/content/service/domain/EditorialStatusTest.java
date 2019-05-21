package com.salesplay.content.service.domain;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@SpringBootTest
public class EditorialStatusTest {

    @Test
    public void mapEnumToString() throws Exception {
        assertThat(EditorialStatus.APPROVED.getValue(), is(notNullValue()));
        assertThat(EditorialStatus.PUBLISHED.getValue(), is(notNullValue()));
        assertThat(EditorialStatus.REVIEW.getValue(), is(notNullValue()));
        assertThat(EditorialStatus.ARCHIVE.getValue(), is(notNullValue()));
        assertThat(EditorialStatus.REJECTED.getValue(), is(notNullValue()));
    }

}
