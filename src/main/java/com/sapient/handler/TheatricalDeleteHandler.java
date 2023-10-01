package com.sapient.handler;

import com.sapient.service.impl.TheatricalServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDate;

@Component
@Slf4j
public class TheatricalDeleteHandler {

  private final TheatricalServiceImpl theatreService;

  @Autowired
  public TheatricalDeleteHandler(TheatricalServiceImpl service) {

    this.theatreService = service;
  }

  public Mono<ServerResponse> deleteById(ServerRequest serverRequest) {

    String docId = serverRequest.pathVariable("doc_id");

    return theatreService
        .deleteById(docId)
        .subscribeOn(Schedulers.boundedElastic())
        .flatMap(this::getSuccessResponse)
        .onErrorResume(this::getErrorResponse);
  }

  public Mono<ServerResponse> deleteByDateAndTheater(ServerRequest serverRequest) {

    String dateJson = serverRequest.queryParam("date").get();
    String theater_name = serverRequest.queryParam("theater_name").get();

    return theatreService
        .deleteByDateAndTheater(LocalDate.parse(dateJson), theater_name)
        .subscribeOn(Schedulers.boundedElastic())
        .flatMap(this::getSuccessResponse)
        .onErrorResume(this::getErrorResponse);
  }

  /*public Flux<ServerResponse> deleteByDateAndCompany(ServerRequest serverRequest) {

    String dateJson = serverRequest.queryParam("date").get();
    String company = serverRequest.queryParam("company").get();

    return theatreService
        .deleteByDateAndCompany(LocalDate.parse(dateJson), company)
        .subscribeOn(Schedulers.boundedElastic())
        .flatMap(this::getSuccessResponseFlux)
        .onErrorResume(this::getErrorResponse);
  }*/

  private Mono<ServerResponse> getSuccessResponseFlux(Void unused) {

    return ServerResponse.noContent().build();
  }

  private Mono<ServerResponse> getErrorResponse(Throwable throwable) {

    return ServerResponse.status(400).bodyValue(throwable.getCause());
  }

  private Mono<ServerResponse> getSuccessResponse(Object response) {
    return ServerResponse.noContent().build();
  }
}
