package com.edwise.pocs.swagger2.controller;

import com.edwise.pocs.swagger2.entity.Info;
import com.edwise.pocs.swagger2.service.InfoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InfoControllerTest {

    private static final Long INFO_ID_1234 = 1234L;
    private static final String INFO_TEST = "Test 1234";
    private static final LocalDateTime INFO_CREATION_DATE_TIME =
            LocalDateTime.of(2011, 12, 9, 19, 15, 20);
    private static final Long INFO_ID_3456 = 3456L;
    private static final String INFO_TEST_2 = "Test 3456";
    private static final LocalDateTime INFO_CREATION_DATE_TIME_2 =
            LocalDateTime.of(2015, 7, 10, 15, 45, 15);

    @Mock
    private InfoService infoService;

    @InjectMocks
    private InfoController infoController;

    @Before
    public void setUp() {
        RequestContextHolder.setRequestAttributes(
                new ServletRequestAttributes(new MockHttpServletRequest()));
    }

    @Test
    public void testGetInfo() {
        when(infoService.findOne(INFO_ID_1234))
                .thenReturn(createMockInfo(INFO_ID_1234, INFO_TEST, INFO_CREATION_DATE_TIME));

        Info info = infoController.getInfo(INFO_ID_1234);

        assertThat(info, is(notNullValue()));
        assertThat(info.getId(), is(INFO_ID_1234));
        verify(infoService).findOne(INFO_ID_1234);
    }

    @Test
    public void testCreateInfo() {
        Info infoToSave = createMockInfo(null, INFO_TEST, INFO_CREATION_DATE_TIME);
        Info infoSaved = createMockInfo(INFO_ID_1234, INFO_TEST, INFO_CREATION_DATE_TIME);
        when(infoService.save(infoToSave)).thenReturn(infoSaved);

        ResponseEntity<Info> response = infoController.createInfo(infoToSave);

        assertThat(response, is(notNullValue()));
        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(response.getBody(), is(nullValue()));
        assertThat(
                response.getHeaders().getLocation().toString(),
                endsWith("/" + INFO_ID_1234));
        verify(infoService).save(infoToSave);
    }

    @Test
    public void testDeleteInfo() {
        infoController.deleteInfo(INFO_ID_1234);

        verify(infoService).delete(INFO_ID_1234);
    }

    @Test
    public void testGetAllInfos() {
        when(infoService.findAll())
                .thenReturn(Arrays.asList(
                        createMockInfo(INFO_ID_1234, INFO_TEST, INFO_CREATION_DATE_TIME),
                        createMockInfo(INFO_ID_3456, INFO_TEST_2, INFO_CREATION_DATE_TIME_2))
                );

        List<Info> infos = infoController.getAllInfos();

        assertThat(infos, hasSize(2));
        verify(infoService).findAll();
    }

    @Test
    public void testUpdateInfo() {
        Info infoToUpdate = createMockInfo(null, INFO_TEST, INFO_CREATION_DATE_TIME);
        Info infoUpdated = createMockInfo(INFO_ID_1234, INFO_TEST, INFO_CREATION_DATE_TIME);
        when(infoService.update(infoToUpdate)).thenReturn(infoUpdated);

        infoController.updateInfo(INFO_ID_1234, infoToUpdate);

        verify(infoService).update(infoToUpdate);
    }

    private Info createMockInfo(Long id, String info, LocalDateTime creationDateTime) {
        return new Info()
                .setId(id)
                .setInfoText(info)
                .setCreationDateTime(creationDateTime);
    }
}