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
public class TheatricalModifyHandler {

  private final TheatricalServiceImpl theatreService;

  @Autowired
  public TheatricalModifyHandler(TheatricalServiceImpl service) {

    this.theatreService = service;
  }

  public Mono<ServerResponse> replaceById(ServerRequest serverRequest) {

    String docId = serverRequest.pathVariable("doc_id");

    Mono<Theatre> inputDoc = serverRequest.bodyToMono(Theatre.class);

    return inputDoc
        .flatMap(
            doc -> {
              Mono<Theatre> theaterFetched = theatreService.findById(docId);
              return theaterFetched.flatMap(
                  existingDoc -> {
                    doc.setId(existingDoc.getId());
                    return theatreService.saveData(doc);
                  });
            })
        .subscribeOn(Schedulers.boundedElastic())
        .flatMap(this::getSuccessResponse)
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  private Mono<ServerResponse> getSuccessResponse(Object response) {

    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(response);
  }

  public Mono<ServerResponse> modifyShowsById(ServerRequest serverRequest) {

    return null;
  }
}
