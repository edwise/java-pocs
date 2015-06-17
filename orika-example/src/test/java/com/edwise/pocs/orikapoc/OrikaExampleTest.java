package com.edwise.pocs.orikapoc;

import com.edwise.pocs.orikapoc.config.OrikaConfig;
import com.edwise.pocs.orikapoc.dto.DestinationDTO;
import com.edwise.pocs.orikapoc.entity.EntityType;
import com.edwise.pocs.orikapoc.entity.SourceEntity;
import ma.glasnost.orika.MapperFacade;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class OrikaExampleTest {

    private static final String ENTITY_NAME = "entityName";
    private static final String SOURCE_SURNAME = "surnameSource";
    private static final int ENTITY_TYPE_ID = 9876;
    private static final String OPT1 = "op1";
    private static final String OPT2 = "op2";
    private static final String OPT3 = "op3";
    private static final int FIRST_NUM = 123;
    private static final int SECOND_NUM = 456;
    private static final int THIRD_NUM = 789;

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
                .setName(ENTITY_NAME);

        DestinationDTO destDTO = mapper.map(sourceEntity, DestinationDTO.class);

        assertThat(destDTO.getName(), is(ENTITY_NAME));
    }

    @Test
    public void shouldMapUserSurnameToSurname() {
        sourceEntity
                .setUserSurname(SOURCE_SURNAME);

        DestinationDTO destDTO = mapper.map(sourceEntity, DestinationDTO.class);

        assertThat(destDTO.getSurname(), is(SOURCE_SURNAME));
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
                .setEntityType(new EntityType().setId(ENTITY_TYPE_ID).setType("Type 9"));

        DestinationDTO destDTO = mapper.map(sourceEntity, DestinationDTO.class);

        assertThat(destDTO.getType(), is(ENTITY_TYPE_ID));
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
                .setOptions(new String[]{OPT1, OPT2, OPT3});

        DestinationDTO destDTO = mapper.map(sourceEntity, DestinationDTO.class);

        assertThat(destDTO.getOptions(), Matchers.contains(OPT1, OPT2, OPT3));
    }

    @Test
    public void shouldMapNumsArrayToFirstNum() {
        sourceEntity
                .setNums(new Integer[]{FIRST_NUM, SECOND_NUM, THIRD_NUM});

        DestinationDTO destDTO = mapper.map(sourceEntity, DestinationDTO.class);

        assertThat(destDTO.getFirstNum(), is(FIRST_NUM));
    }
}