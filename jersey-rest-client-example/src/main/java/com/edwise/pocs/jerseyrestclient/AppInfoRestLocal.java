package com.edwise.pocs.jerseyrestclient;

import com.edwise.pocs.jerseyrestclient.model.Info;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.Arrays;

public class AppInfoRestLocal {

    private static final String URL_BASE_API = "http://localhost:8080/api/";
    private static final String INFO_RESOURCE = "info/";

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Client client = ClientBuilder.newClient(
                new ClientConfig().register(new JacksonJsonProvider(mapper)));
        WebTarget webTarget = client.target(URL_BASE_API).path(INFO_RESOURCE);

        getOneInfo(webTarget);
        getAllInfos(webTarget);
        postOneInfo(webTarget);
        putOneInfo(webTarget);
        deleteInfo(webTarget);
    }

    private static void getOneInfo(WebTarget webTarget) {
        Response response = webTarget.path("12")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();

        System.out.println();
        System.out.println("GET one executed");
        System.out.println("GET StatusCode = " + response.getStatus());
        System.out.println("GET Headers = " + response.getHeaders());
        System.out.println("GET Body (object): "
                + response.readEntity(Info.class));
    }

    private static void getAllInfos(WebTarget webTarget) {
        Response response = webTarget
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();

        System.out.println();
        System.out.println("GET All StatusCode = " + response.getStatus());
        System.out.println("GET All Headers = " + response.getHeaders());
        System.out.println("GET Body (object list): ");
        Arrays.asList(response.readEntity(Info[].class))
                .forEach(info -> System.out.println("--> " + info));
    }

    private static void postOneInfo(WebTarget webTarget) {
        Info infoToInsert =
                createInfo(null, "New text", LocalDateTime.of(2015, 5, 15, 21, 25, 30));
        Response response = webTarget
                .request()
                .post(Entity.entity(infoToInsert, MediaType.APPLICATION_JSON_TYPE));

        System.out.println();
        System.out.println("POST executed");
        System.out.println("POST StatusCode = " + response.getStatus());
        System.out.println("POST Body (object): "
                + response.readEntity(Info.class));
    }

    private static void putOneInfo(WebTarget webTarget) {
        Info infoToUpdate =
                createInfo(123L, "Info updated", LocalDateTime.of(2015, 5, 11, 22, 35, 35));
        Response response = webTarget.path("123")
                .request()
                .put(Entity.entity(infoToUpdate, MediaType.APPLICATION_JSON_TYPE));

        System.out.println();
        System.out.println("PUT StatusCode = " + response.getStatus());
        System.out.println("PUT Headers = " + response.getHeaders());
    }

    private static void deleteInfo(WebTarget webTarget) {
        Response response = webTarget.path("122")
                .request()
                .delete();

        System.out.println();
        System.out.println("DELETE StatusCode = " + response.getStatus());
        System.out.println("DELETE Headers = " + response.getHeaders());
    }

    private static Info createInfo(Long id, String infoText, LocalDateTime dateTime) {
        Info info = new Info();
        info.setId(id);
        info.setInfoText(infoText);
        info.setCreationDateTime(dateTime);

        return info;
    }
}
