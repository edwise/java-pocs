package com.edwise.springbootseries.integrationtests;

import com.edwise.springbootseries.integrationtests.controller.InfoController;
import com.edwise.springbootseries.integrationtests.entity.Info;
import com.edwise.springbootseries.integrationtests.service.InfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InfoControllerTest {
    private static final Long INFO_ID_1234 = 1234l;
    private static final String INFO_TEST = "Test 1234";
    private static final LocalDateTime INFO_CREATION_DATE_TIME = LocalDateTime.of(2011, 12, 9, 19, 15, 20);

    @Mock
    private InfoService infoService;

    @InjectMocks
    private InfoController infoController = new InfoController();

    @Test
    public void testGetInfo() {
        when(infoService.findOne(INFO_ID_1234))
                .thenReturn(createInfo(INFO_ID_1234, INFO_TEST, INFO_CREATION_DATE_TIME));

        Info info = infoController.getInfo(INFO_ID_1234);

        assertNotNull(info);
        assertThat(info.getId(), is(INFO_ID_1234));
        verify(infoService).findOne(INFO_ID_1234);
    }

    @Test
    public void testCreateInfo() {
        Info infoToSave = createInfo(null, INFO_TEST, INFO_CREATION_DATE_TIME);
        Info infoSaved = createInfo(INFO_ID_1234, INFO_TEST, INFO_CREATION_DATE_TIME);
        when(infoService.save(infoToSave)).thenReturn(infoSaved);

        ResponseEntity<Info> response = infoController.createInfo(infoToSave);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(response.getBody().getInfo(), is(INFO_TEST));
        assertThat(response.getBody().getCreationDateTime(), is(INFO_CREATION_DATE_TIME));
        verify(infoService).save(infoToSave);
    }

    @Test
    public void testDeleteInfo() {
        infoController.deleteInfo(INFO_ID_1234);

        verify(infoService).delete(INFO_ID_1234);
    }

    private Info createInfo(Long id, String info, LocalDateTime creationDateTime) {
        return new Info()
                .setId(id)
                .setInfo(info)
                .setCreationDateTime(creationDateTime);
    }
}
