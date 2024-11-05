package com.alex.jsonplaceholder;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class JSONPlaceHolderConfig {

  private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

  @Bean("rest-client")
  JsonPlaceHolderService jsonPlaceHolderRestClientService() {
    RestClient restClient = RestClient.create(BASE_URL);
    return HttpServiceProxyFactory
        .builderFor(RestClientAdapter.create(restClient))
        .build()
        .createClient(JsonPlaceHolderService.class);
  }

  @Primary
  @Bean("web-client")
  JsonPlaceHolderService jsonPlaceHolderWebClientService() {
    // Create connection provider with reasonable defaults
    ConnectionProvider provider = ConnectionProvider.builder("custom")
        .maxConnections(500)
        .maxIdleTime(Duration.ofSeconds(20))
        .maxLifeTime(Duration.ofSeconds(60))
        .pendingAcquireTimeout(Duration.ofSeconds(60))
        .build();

    // Configure HTTP client with DNS resolver and timeouts
    HttpClient httpClient = HttpClient.create(provider)
        .resolver(DefaultAddressResolverGroup.INSTANCE)
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
        .responseTimeout(Duration.ofSeconds(10))
        .doOnConnected(conn -> conn
            .addHandlerLast(new ReadTimeoutHandler(10, TimeUnit.SECONDS))
            .addHandlerLast(new WriteTimeoutHandler(10, TimeUnit.SECONDS)));

    // Create WebClient with the configured HttpClient
    WebClient webClient = WebClient.builder()
        .clientConnector(new ReactorClientHttpConnector(httpClient))
        .baseUrl(BASE_URL)
        .defaultStatusHandler(HttpStatusCode::isError, clientResponse -> {
          return Mono.error(new ResponseStatusException(
              clientResponse.statusCode()));
        })
        .defaultHeader(HttpHeaders.USER_AGENT, "Spring WebClient")
        .defaultHeader(HttpHeaders.ACCEPT, "application/json")
        .build();

    return HttpServiceProxyFactory
        .builderFor(WebClientAdapter.create(webClient))
        .build()
        .createClient(JsonPlaceHolderService.class);
  }
}
