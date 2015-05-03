package com.edwise.pocs.springrestclient;

import com.edwise.pocs.springrestclient.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class AppBookRestApiary {

    private final static String URL_API_BOOKS = "http://private-114e-booksapi.apiary-mock.com/books/";

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        getOneBook(restTemplate);
        getAllBooks(restTemplate);
        postOneBook(restTemplate);
        putOneBook(restTemplate);
        deleteBook(restTemplate);
    }

    private static void getOneBook(RestTemplate restTemplate) {
        ResponseEntity<Book> response = restTemplate.getForEntity(URL_API_BOOKS + "{id}", Book.class, 12);

        System.out.println();
        System.out.println("GET StatusCode = " + response.getStatusCode());
        System.out.println("GET Headers = " + response.getHeaders());
        System.out.println("GET Body (object): " + response.getBody());
    }

    private static void getAllBooks(RestTemplate restTemplate) {
        ResponseEntity<Book[]> response = restTemplate.getForEntity(URL_API_BOOKS, Book[].class);

        System.out.println();
        System.out.println("GET All StatusCode = " + response.getStatusCode());
        System.out.println("GET All Headers = " + response.getHeaders());
        System.out.println("GET Body (object list): ");
        Arrays.asList(response.getBody()).forEach(book -> System.out.println("--> " + book));
    }

    private static void postOneBook(RestTemplate restTemplate) {
        // TODO

        System.out.println();
        System.out.println("POST executed");
    }

    private static void putOneBook(RestTemplate restTemplate) {
        // TODO

        System.out.println();
        System.out.println("PUT executed");
    }

    private static void deleteBook(RestTemplate restTemplate) {
        restTemplate.delete(URL_API_BOOKS + "/{id}", 12);

        System.out.println();
        System.out.println("DELETE executed");
    }
}
