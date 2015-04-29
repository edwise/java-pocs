package com.edwise.pocs.springrestclient;

import com.edwise.pocs.springrestclient.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class Application {

    private final static String URL_API_BOOKS = "http://private-114e-booksapi.apiary-mock.com/books";

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        getOneBook(restTemplate);
        getAllBooks(restTemplate);
        deleteBook(restTemplate);
    }

    private static void getOneBook(RestTemplate restTemplate) {
        ResponseEntity<Book> book = restTemplate.getForEntity(URL_API_BOOKS + "/{id}", Book.class, 12);

        System.out.println();
        System.out.println("GET StatusCode = " + book.getStatusCode());
        System.out.println("GET Headers = " + book.getHeaders());
        System.out.println("GET Body (object): " + book.getBody());
    }

    private static void getAllBooks(RestTemplate restTemplate) {
        ResponseEntity<Book[]> books = restTemplate.getForEntity(URL_API_BOOKS, Book[].class);

        System.out.println();
        System.out.println("GET All StatusCode = " + books.getStatusCode());
        System.out.println("GET All Headers = " + books.getHeaders());
        System.out.println("GET Body (object list): ");
        Arrays.asList(books.getBody()).forEach(book -> System.out.println("--> " + book));
    }

    private static void deleteBook(RestTemplate restTemplate) {
        restTemplate.delete(URL_API_BOOKS + "/{id}", 12);

        System.out.println();
    }
}
