package com.edwise.pocs.springrestclient;

import com.edwise.pocs.springrestclient.model.Info;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;

public class AppInfoRestLocal {

    private static final String URL_API_INFO = "http://localhost:8080//api/info/";

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        getOneInfo(restTemplate);
        getAllInfos(restTemplate);
        postOneInfo(restTemplate);
        putOneInfo(restTemplate);
        deleteInfo(restTemplate);
    }

    private static void getOneInfo(RestTemplate restTemplate) {
        ResponseEntity<Info> response = restTemplate.getForEntity(URL_API_INFO + "{id}", Info.class, 12);

        System.out.println();
        System.out.println("GET one executed");
        System.out.println("GET StatusCode = " + response.getStatusCode());
        System.out.println("GET Headers = " + response.getHeaders());
        System.out.println("GET Body (object): " + response.getBody());
    }

    private static void getAllInfos(RestTemplate restTemplate) {
        ResponseEntity<Info[]> response = restTemplate.getForEntity(URL_API_INFO, Info[].class);

        System.out.println();
        System.out.println("GET All executed");
        System.out.println("GET All StatusCode = " + response.getStatusCode());
        System.out.println("GET All Headers = " + response.getHeaders());
        System.out.println("GET Body (object list): ");
        Arrays.asList(response.getBody()).forEach(info -> System.out.println("--> " + info));
    }

    private static void postOneInfo(RestTemplate restTemplate) {
        Info infoToInsert = createInfo(null, "UNew info text", LocalDateTime.of(2015, 5, 15, 21, 25, 30));
        ResponseEntity<Info> response = restTemplate.postForEntity(URL_API_INFO, infoToInsert, Info.class);

        System.out.println();
        System.out.println("POST executed");
        System.out.println("POST StatusCode = " + response.getStatusCode());
        System.out.println("POST Headers = " + response.getHeaders());
        System.out.println("POST Body (object): " + response.getBody());
    }

    private static void putOneInfo(RestTemplate restTemplate) {
        Info infoToUpdate = createInfo(123L, "Updated text", LocalDateTime.of(2015, 5, 11, 22, 35, 35));
        restTemplate.put(URL_API_INFO + "{id}", infoToUpdate, 123L);

        System.out.println();
        System.out.println("PUT executed");
    }

    private static void deleteInfo(RestTemplate restTemplate) {
        restTemplate.delete(URL_API_INFO + "{id}", 12);

        System.out.println();
        System.out.println("DELETE executed");
    }

    private static Info createInfo(Long id, String infoText, LocalDateTime dateTime) {
        Info info = new Info();
        info.setId(id);
        info.setInfoText(infoText);
        info.setCreationDateTime(dateTime);

        return info;
    }
}
