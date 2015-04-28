package com.edwise.pocs.springrestclient;

import com.edwise.pocs.springrestclient.config.RestTemplateFactory;
import com.edwise.pocs.springrestclient.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class Application {

    private final static String URL_API_BOOKS = "http://private-114e-booksapi.apiary-mock.com/books";

    public static void main(String[] args) {
        RestTemplate restTemplate = RestTemplateFactory.createRestTemplate();

        getOneBook(restTemplate);
        getAllBooks(restTemplate);

        testArray();
    }

    private static void testArray() {
        int[][] matrix = new int[10][10];
        matrix[0][0] = 24;
        matrix[0] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        matrix[1] = new int[]{1, 12, 13, 14, 15, 16, 17, 18, 19, 20};

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void getOneBook(RestTemplate restTemplate) {
        ResponseEntity<Book> book = restTemplate.getForEntity(URL_API_BOOKS + "/{id}", Book.class, 12);

        System.out.println("StatusCode = " + book.getStatusCode());
        System.out.println("Headers = " + book.getHeaders());
        System.out.println("Book getted: " + book.getBody());
    }

    private static void getAllBooks(RestTemplate restTemplate) {
        ResponseEntity<Book[]> books = restTemplate.getForEntity(URL_API_BOOKS, Book[].class);

        System.out.println("StatusCode = " + books.getStatusCode());
        System.out.println("Headers = " + books.getHeaders());
        Arrays.asList(books.getBody()).forEach(System.out::println);
    }
}
