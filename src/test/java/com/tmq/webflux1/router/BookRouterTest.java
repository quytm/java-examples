package com.tmq.webflux1.router;

import com.tmq.webflux1.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookRouterTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testExampleOneStepFurther() {
        Book book = new Book("123", "Title", "Author");
        webTestClient
                .post().uri("/book")
                .syncBody(book)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class).isEqualTo(book);

        Book[] books = new Book[]{book};
        webTestClient
                .get().uri("/books")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book[].class).isEqualTo(books);
    }

}
