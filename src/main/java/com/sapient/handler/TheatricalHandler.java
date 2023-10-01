package com.sapient.handler;

import com.sapient.model.Theatre;
import com.sapient.service.impl.TheatricalServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
@Slf4j
public class TheatricalHandler {

  private final TheatricalServiceImpl theatreService;

  @Autowired
  public TheatricalHandler(TheatricalServiceImpl service) {

    this.theatreService = service;
  }

  public Mono<ServerResponse> updateData(ServerRequest serverRequest) {

    return null;
  }

  public Mono<ServerResponse> postData(ServerRequest serverRequest) {

    /*return serverRequest.bodyToMono(new ParameterizedTypeReference<List<Theatre>>() {})
    .flatMap(
            data ->
            theatreService.saveData(data))*/
    return serverRequest
        .bodyToMono(Theatre.class)
        .flatMap(data -> theatreService.saveData(data))
        .subscribeOn(Schedulers.boundedElastic())
        .flatMap(this::getSuccessResponse)
        .onErrorResume(this::getErrorResponse);
  }

  private Mono<ServerResponse> getErrorResponse(Throwable throwable) {
    return null;
  }

  private Mono<ServerResponse> getSuccessResponse(Object response) {

    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(response);
  }
}
