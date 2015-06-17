package com.edwise.pocs.orikapoc;

import com.edwise.pocs.orikapoc.config.OrikaConfig;
import com.edwise.pocs.orikapoc.dto.DestinationDTO;
import com.edwise.pocs.orikapoc.entity.EntityType;
import com.edwise.pocs.orikapoc.entity.SourceEntity;
import ma.glasnost.orika.MapperFacade;

import java.time.LocalDateTime;

public class OrikaExample {

    public static void main(String[] args) {
        MapperFacade mapper = OrikaConfig.getMapperFacade();

        SourceEntity sourceEntity = new SourceEntity();
        sourceEntity
                .setId(12345L)
                .setName("entityName")
                .setUserSurname("surnameEntity")
                .setEntityType(new EntityType().setId(9999).setType("type9"))
                .setOptions(new String[]{"option1", "option2", "option3"})
                .setCreationDateTime(LocalDateTime.of(2015, 6, 16, 9, 10, 30))
                .setNums(new Integer[]{14, 25, 67});

        DestinationDTO destDTO = mapper.map(sourceEntity, DestinationDTO.class);

        System.out.println("Source: " + sourceEntity);
        System.out.println("Destination: " + destDTO);
    }
}
