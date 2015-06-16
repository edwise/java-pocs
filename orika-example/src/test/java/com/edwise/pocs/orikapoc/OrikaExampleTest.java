package com.edwise.pocs.orikapoc;

import com.edwise.pocs.orikapoc.config.OrikaConfig;
import com.edwise.pocs.orikapoc.dto.DestinationDTO;
import com.edwise.pocs.orikapoc.entity.EntityType;
import com.edwise.pocs.orikapoc.entity.SourceEntity;
import ma.glasnost.orika.MapperFacade;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class OrikaExampleTest {

    private MapperFacade mapper;

    private SourceEntity sourceEntity;

    @Before
    public void setUp() {
        mapper = OrikaConfig.getMapperFacade();
        sourceEntity = new SourceEntity();
    }

    @Test
    public void shouldMapNameToNameByDefault() {
        sourceEntity
                .setName("entityName");

        DestinationDTO destDTO = mapper.map(sourceEntity, DestinationDTO.class);

        assertThat(destDTO.getName(), is("entityName"));
    }

    @Test
    public void shouldMapUserSurnameToSurname() {
        sourceEntity
                .setUserSurname("surnameSource");

        DestinationDTO destDTO = mapper.map(sourceEntity, DestinationDTO.class);

        assertThat(destDTO.getSurname(), is("surnameSource"));
    }

    @Test
    public void shouldNotMapId() {
        sourceEntity
                .setId(12345678L);

        DestinationDTO destDTO = mapper.map(sourceEntity, DestinationDTO.class);

        assertThat(destDTO.getId(), is(nullValue()));
    }

    @Test
    public void shouldMapEntityIdToId() {
        sourceEntity
                .setEntityType(new EntityType().setId(9876).setType("Type 9"));

        DestinationDTO destDTO = mapper.map(sourceEntity, DestinationDTO.class);

        assertThat(destDTO.getType(), is(9876));
    }

    @Test
    public void shouldMapLocalDateTimeToLocalDateWithConverter() {
        sourceEntity
                .setCreationDateTime(LocalDateTime.of(2015, 6, 16, 9, 10, 30));

        DestinationDTO destDTO = mapper.map(sourceEntity, DestinationDTO.class);

        assertThat(destDTO.getCreationDate(), is(LocalDate.of(2015, 6, 16)));
    }

    @Test
    public void shouldMapStringArrayToStringList() {
        sourceEntity
                .setOptions(new String[]{"op1", "op2", "op3"});

        DestinationDTO destDTO = mapper.map(sourceEntity, DestinationDTO.class);

        assertThat(destDTO.getOptions(), contains("op1", "op2", "op3"));
    }

    @Test
    public void shouldMapNumsArrayToFirstNum() {
        sourceEntity
                .setNums(new Integer[]{123, 456, 789});

        DestinationDTO destDTO = mapper.map(sourceEntity, DestinationDTO.class);

        assertThat(destDTO.getFirstNum(), is(123));
    }
}