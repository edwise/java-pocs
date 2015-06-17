package com.edwise.pocs.orikapoc.config;

import com.edwise.pocs.orikapoc.dto.DestinationDTO;
import com.edwise.pocs.orikapoc.entity.SourceEntity;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public final class OrikaConfig {

    private static final MapperFactory mapperFactory;

    static {
        mapperFactory = new DefaultMapperFactory.Builder().build();

        ConverterFactory converterFactory = mapperFactory.getConverterFactory();
        converterFactory.registerConverter(new LocalDateTimeToLocalDateConverter());

        mapperFactory.classMap(SourceEntity.class, DestinationDTO.class)
                .exclude("id")
                .field("userSurname", "surname")
                .field("entityType.id", "type")
                .field("creationDateTime", "creationDate")
                .field("nums[0]", "firstNum")
                .byDefault()
                .register();
    }

    private OrikaConfig() {
    }

    public static MapperFacade getMapperFacade() {
        return mapperFactory.getMapperFacade();
    }
}
