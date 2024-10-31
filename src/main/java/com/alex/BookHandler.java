package com.alex;

import jakarta.servlet.ServletRequest;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.List;


public class BookHandler {
    public ServerResponse getAllBooks(ServerRequest request) {
        return ServerResponse.ok().body(List.of(new BookRouteConfig.Book(
                            1 ,"Harry Porter", "J.K.Rolling"

                    )));
    }

    public ServerResponse getBookById(ServerRequest request) {
        Integer id = Integer.parseInt(request.pathVariable("id"));
        return ServerResponse.ok().body(List.of(new BookRouteConfig.Book(
                            1, "1, Harry Porter", "J.K.Rolling"
                    ))
                .stream()
                .filter(book -> book.id().equals(id))
        );

    }
}




