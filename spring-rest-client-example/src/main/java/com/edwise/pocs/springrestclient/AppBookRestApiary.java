package com.edwise.pocs.springrestclient;

import com.edwise.pocs.springrestclient.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class AppBookRestApiary {

    private static final String URL_API_BOOKS =
            "http://private-114e-booksapi.apiary-mock.com/books/";

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        getOneBook(restTemplate);
        getAllBooks(restTemplate);
        postOneBook(restTemplate);
        putOneBook(restTemplate);
        deleteBook(restTemplate);
    }

    private static void getOneBook(RestTemplate restTemplate) {
        ResponseEntity<Book> response =
                restTemplate.getForEntity(URL_API_BOOKS + "{id}", Book.class, 12L);

        System.out.println();
        System.out.println("GET StatusCode = " + response.getStatusCode());
        System.out.println("GET Headers = " + response.getHeaders());
        System.out.println("GET Body (object): " + response.getBody());
    }

    private static void getAllBooks(RestTemplate restTemplate) {
        ResponseEntity<Book[]> response =
                restTemplate.getForEntity(URL_API_BOOKS, Book[].class);

        System.out.println();
        System.out.println("GET All StatusCode = " + response.getStatusCode());
        System.out.println("GET All Headers = " + response.getHeaders());
        System.out.println("GET Body (object list): ");
        Arrays.asList(response.getBody())
                .forEach(book -> System.out.println("--> " + book));
    }

    private static void postOneBook(RestTemplate restTemplate) {
        Book bookToInsert = createBook(null, "New book title");
        ResponseEntity<Book> response =
                restTemplate.postForEntity(URL_API_BOOKS, bookToInsert, Book.class);

        System.out.println();
        System.out.println("POST executed");
        System.out.println("POST StatusCode = " + response.getStatusCode());
        System.out.println("POST Header location = " + response.getHeaders().getLocation());
    }

    private static void putOneBook(RestTemplate restTemplate) {
        Book bookToUpdate = createBook(123L, "Book title updated");
        restTemplate.put(URL_API_BOOKS + "{id}", bookToUpdate, 123L);

        System.out.println();
        System.out.println("PUT executed");
    }

    private static void deleteBook(RestTemplate restTemplate) {
        restTemplate.delete(URL_API_BOOKS + "{id}", 12L);

        System.out.println();
        System.out.println("DELETE executed");
    }

    private static Book createBook(Long id, String title) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);

        return book;
    }
}
