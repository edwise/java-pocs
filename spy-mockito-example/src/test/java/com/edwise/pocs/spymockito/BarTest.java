package com.edwise.pocs.spymockito;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class BarTest {

    private Bar bar;

    @Test
    public void testMyMethodWithoutMockito() {
        bar = new Bar();

        int result = bar.myMethod();

        assertThat(result).isEqualTo(1);
    }

    @Test
    public void testMyMethodWithMockitoSpy() {
        bar = spy(new Bar());
        doReturn("OK Foo mocked").when(bar).doSomethingWithDatabase();
//        when(Foo.doSomethingWithDatabase()).thenReturn("OK");

        int result = bar.myMethod();

        assertThat(result).isEqualTo(1);
    }
}