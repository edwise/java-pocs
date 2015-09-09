package com.edwise.pocs.jerseyrestclient;

import com.edwise.pocs.jerseyrestclient.model.Book;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;

public class AppBookRestApiary {

    private static final String URL_BASE_API =
            "http://private-114e-booksapi.apiary-mock.com/";
    private static final String BOOKS_RESOURCE = "books/";

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient(
                new ClientConfig().register(JacksonJsonProvider.class));
        WebTarget webTarget = client.target(URL_BASE_API).path(BOOKS_RESOURCE);

        getOneBook(webTarget);
        getAllBooks(webTarget);
        postOneBook(webTarget);
        putOneBook(webTarget);
        deleteBook(webTarget);
    }

    private static void getOneBook(WebTarget webTarget) {
        Response response = webTarget.path("12")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();

        System.out.println();
        System.out.println("GET StatusCode = " + response.getStatus());
        System.out.println("GET Headers = " + response.getHeaders());
        System.out.println("GET Body (object): "
                + response.readEntity(Book.class));
    }

    private static void getAllBooks(WebTarget webTarget) {
        Response response = webTarget
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();

        System.out.println();
        System.out.println("GET All StatusCode = " + response.getStatus());
        System.out.println("GET All Headers = " + response.getHeaders());
        System.out.println("GET Body (object list): ");
        Arrays.asList(response.readEntity(Book[].class))
                .forEach(book -> System.out.println("--> " + book));
    }

    private static void postOneBook(WebTarget webTarget) {
        Book bookToInsert = createBook(null, "New book title");
        Response response = webTarget
                .request()
                .post(Entity.entity(bookToInsert, MediaType.APPLICATION_JSON_TYPE));

        System.out.println();
        System.out.println("POST executed");
        System.out.println("POST StatusCode = " + response.getStatus());
        System.out.println("POST Header location = "
                + response.getHeaders().get("location"));
    }

    private static void putOneBook(WebTarget webTarget) {
        Book bookToUpdate = createBook(123L, "Book title updated");
        Response response = webTarget.path("123")
                .request()
                .put(Entity.entity(bookToUpdate, MediaType.APPLICATION_JSON_TYPE));

        System.out.println();
        System.out.println("PUT StatusCode = " + response.getStatus());
        System.out.println("PUT Headers = " + response.getHeaders());
    }

    private static void deleteBook(WebTarget webTarget) {
        Response response = webTarget.path("12")
                .request()
                .delete();

        System.out.println();
        System.out.println("DELETE StatusCode = " + response.getStatus());
        System.out.println("DELETE Headers = " + response.getHeaders());
    }

    private static Book createBook(Long id, String title) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);

        return book;
    }
}
