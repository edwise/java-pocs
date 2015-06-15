package com.edwise.pocs.orikapoc;

import com.edwise.pocs.orikapoc.config.OrikaConfig;
import ma.glasnost.orika.MapperFacade;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrikaExampleTest {

    private MapperFacade mapper;

    @Before
    public void setUp() {
        mapper = OrikaConfig.getMapperFacade();
    }

    @Test
    public void testApplication() {
        fail("Not yet implemented");
    }
}