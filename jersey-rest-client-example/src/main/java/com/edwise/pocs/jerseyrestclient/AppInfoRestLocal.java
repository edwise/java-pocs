package com.edwise.pocs.jerseyrestclient;

import com.edwise.pocs.jerseyrestclient.model.Info;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AppInfoRestLocal {


    private static final String URL_API = "http://localhost:8080/api/";
    private static final String INFO_RESOURCE = "info/";

    private static ObjectMapper MAPPER;

    public static void main(String[] args) {
        MAPPER = new ObjectMapper();
        MAPPER.registerModule(new JSR310Module());
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        Client client = ClientBuilder.newClient(
                new ClientConfig().register(JacksonJsonProvider.class).register(MAPPER));
        WebTarget webTarget = client.target(URL_API).path(INFO_RESOURCE);

        getOneInfo(webTarget);
//        getAllInfos(webTarget);
//        postOneInfo(webTarget);
//        putOneInfo(webTarget);
//        deleteInfo(webTarget);
    }

    private static void getOneInfo(WebTarget webTarget) {
        Response response = webTarget.path("12").request(MediaType.APPLICATION_JSON_TYPE).get();

        System.out.println();
        System.out.println("GET one executed");
        System.out.println("GET StatusCode = " + response.getStatus());
        System.out.println("GET Headers = " + response.getHeaders());
        System.out.println("GET Body (object): " + response.readEntity(Info.class));
    }

//    private static void getAllInfos(WebTarget webTarget) {
//        ResponseEntity<Info[]> response = webTarget.getForEntity(URL_API_INFO, Info[].class);
//
//        System.out.println();
//        System.out.println("GET All executed");
//        System.out.println("GET All StatusCode = " + response.getStatusCode());
//        System.out.println("GET All Headers = " + response.getHeaders());
//        System.out.println("GET Body (object list): ");
//        Arrays.asList(response.getBody()).forEach(info -> System.out.println("--> " + info));
//    }
//
//    private static void postOneInfo(WebTarget webTarget) {
//        Info infoToInsert = createInfo(null, "UNew info text", LocalDateTime.of(2015, 5, 15, 21, 25, 30));
//        ResponseEntity<Info> response = webTarget.postForEntity(URL_API_INFO, infoToInsert, Info.class);
//
//        System.out.println();
//        System.out.println("POST executed");
//        System.out.println("POST StatusCode = " + response.getStatusCode());
//        System.out.println("POST Headers = " + response.getHeaders());
//        System.out.println("POST Body (object): " + response.getBody());
//    }
//
//    private static void putOneInfo(WebTarget webTarget) {
//        Info infoToUpdate = createInfo(123L, "Updated text", LocalDateTime.of(2015, 5, 11, 22, 35, 35));
//        webTarget.put(URL_API_INFO + "{id}", infoToUpdate, 123L);
//
//        System.out.println();
//        System.out.println("PUT executed");
//    }
//
//    private static void deleteInfo(WebTarget webTarget) {
//        webTarget.delete(URL_API_INFO + "{id}", 12);
//
//        System.out.println();
//        System.out.println("DELETE executed");
//    }
//
//    private static Info createInfo(Long id, String infoText, LocalDateTime dateTime) {
//        Info info = new Info();
//        info.setId(id);
//        info.setInfoText(infoText);
//        info.setCreationDateTime(dateTime);
//
//        return info;
//    }
}
