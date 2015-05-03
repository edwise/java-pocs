package com.edwise.pocs.springrestclient;

import com.edwise.pocs.springrestclient.model.Info;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class AppInfoRestLocal {

    private final static String URL_API_INFO = "http://localhost:8080//api/info/";

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
        // TODO
        ResponseEntity<Info> response = restTemplate.postForEntity(URL_API_INFO, new Info(), Info.class);

        System.out.println();
        System.out.println("POST executed");
        System.out.println("POST StatusCode = " + response.getStatusCode());
        System.out.println("POST Headers = " + response.getHeaders());
        System.out.println("POST Body (object): " + response.getBody());
    }

    private static void putOneInfo(RestTemplate restTemplate) {
        // TODO

        System.out.println();
        System.out.println("PUT executed");
    }

    private static void deleteInfo(RestTemplate restTemplate) {
        restTemplate.delete(URL_API_INFO + "/{id}", 12);

        System.out.println();
        System.out.println("DELETE executed");
    }
}
