package com.edwise.pocs.spymockito;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BarWellDesignedTest {

    private BarWellDesigned barWellDesigned;

    @Test
    public void testMyMethodWithoutMockito() {
        Foo foo = mock(Foo.class);
        barWellDesigned = new BarWellDesigned(foo);
        when(foo.doSomethingWithDatabase()).thenReturn("OK Foo mocked");

        int result = barWellDesigned.myMethod();

        assertThat(result).isEqualTo(1);
    }
}