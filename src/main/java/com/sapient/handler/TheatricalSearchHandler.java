package com.sapient.handler;


import com.sapient.service.impl.TheatricalServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDate;

@Component
@Slf4j
public class TheatricalSearchHandler {

    private final TheatricalServiceImpl theatreService;

    @Autowired
    public TheatricalSearchHandler(TheatricalServiceImpl service) {

        this.theatreService = service;
    }

    public Mono<ServerResponse> findById(ServerRequest serverRequest) {

        String docId = serverRequest.pathVariable("doc_id");

        return theatreService.findById(docId)
        .subscribeOn(Schedulers.boundedElastic())
                .flatMap(this::getSuccessResponse)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    /*public Flux<ServerResponse> findByDateAndCompany(ServerRequest serverRequest) {

        String dateJson = serverRequest.queryParam("date").get();
        String theater_name = serverRequest.queryParam("theater_name").get();

        return theatreService
                .findByDateAndCompany(LocalDate.parse(dateJson), theater_name)
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(this::getSuccessResponse)
                .switchIfEmpty(ServerResponse.notFound().build());
    }*/

    public Mono<ServerResponse> findByDateAndTheater(ServerRequest serverRequest) {

        String dateJson = serverRequest.queryParam("date").get();
        String theater_name = serverRequest.queryParam("theater_name").get();

        log.info("date : {}, theater_name: {}",dateJson,theater_name);
        return theatreService
                .findByDateAndTheater(LocalDate.parse(dateJson), theater_name)
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(this::getSuccessResponse)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    private Mono<ServerResponse> getSuccessResponse(Object response) {

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(response);
    }



}
