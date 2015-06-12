package com.edwise.pocs.orikapoc.config;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class OrikaConfig {

    private static final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    private OrikaConfig() {
        // TODO orika mappings...
    }

    public static MapperFacade getMapperFacade() {
        return mapperFactory.getMapperFacade();
    }
}
