package com.salesplay.content.service.domain;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@SpringBootTest
public class VisibilityTest {

    @Test
    public void mapEnumToString() throws Exception {
        assertThat(Visibility.PRIVATE.getValue(), is(notNullValue()));
        assertThat(Visibility.PUBLIC.getValue(), is(notNullValue()));
    }

}