package com.sapient.handler;


import com.sapient.exceptions.DocumentNotFoundException;
import com.sapient.exceptions.DataValidationException;
import com.sapient.service.impl.TheatricalServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
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

/*  private Mono<ServerResponse> getSuccessResponseFlux(Void unused) {

    return ServerResponse.noContent().build();
  }*/

  private Mono<ServerResponse> getErrorResponse(Throwable err) {

    log.error(" Handling Error => {}",err.getStackTrace());
    if(err instanceof DocumentNotFoundException) {
      return ServerResponse.status(HttpStatus.NOT_FOUND).bodyValue(((DocumentNotFoundException) err).getMsg());
    }else if(err instanceof DataValidationException){
      return ServerResponse.status(HttpStatus.BAD_REQUEST).bodyValue(((DataValidationException) err).getMsg());
    }else {
      return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(err.getMessage());
    }
  }

  private Mono<ServerResponse> getSuccessResponse(Object response) {
    return ServerResponse.noContent().build();
  }
}
