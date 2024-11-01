package com.alex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class BookRouteConfig {

  record Book(Integer id, String name, String author) {
  }

  @Bean
  public RouterFunction<ServerResponse> routerFunction() {

    BookHandler bookHandler = new BookHandler();

    return RouterFunctions.route()
        .GET("/api/v2/books", bookHandler::getAllBooks)
        .GET("/api/v2/books/{id}", bookHandler::getBookById)
        .build();

  }
}
