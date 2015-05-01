package com.edwise.pocs.springrestclient;

import com.edwise.pocs.springrestclient.model.Info;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class AppInfoRestLocal {

    private final static String URL_API_INFO = "http://localhost:8080//api/info/";

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        getOneInfo(restTemplate);
        getAllInfos(restTemplate);
        deleteInfo(restTemplate);
    }

    private static void getOneInfo(RestTemplate restTemplate) {
        ResponseEntity<Info> info = restTemplate.getForEntity(URL_API_INFO + "{id}", Info.class, 12);

        System.out.println();
        System.out.println("GET one executed");
        System.out.println("GET StatusCode = " + info.getStatusCode());
        System.out.println("GET Headers = " + info.getHeaders());
        System.out.println("GET Body (object): " + info.getBody());
    }

    private static void getAllInfos(RestTemplate restTemplate) {
        ResponseEntity<Info[]> infos = restTemplate.getForEntity(URL_API_INFO, Info[].class);

        System.out.println();
        System.out.println("GET All executed");
        System.out.println("GET All StatusCode = " + infos.getStatusCode());
        System.out.println("GET All Headers = " + infos.getHeaders());
        System.out.println("GET Body (object list): ");
        Arrays.asList(infos.getBody()).forEach(info -> System.out.println("--> " + info));
    }

    private static void deleteInfo(RestTemplate restTemplate) {
        restTemplate.delete(URL_API_INFO + "/{id}", 12);

        System.out.println();
        System.out.println("DELETE executed");
    }
}
