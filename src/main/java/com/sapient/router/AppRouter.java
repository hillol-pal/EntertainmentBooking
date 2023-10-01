package com.sapient.router;

import com.sapient.handler.TheatricalDeleteHandler;
import com.sapient.handler.TheatricalHandler;
import com.sapient.handler.TheatricalSearchHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class AppRouter {

  /* private final TheatricalHandler theatricalHandler;

  @Autowired
  public AppRouter(TheatricalHandler handler) {
      this.theatricalHandler = handler;
  }*/

  @Bean
  public RouterFunction<ServerResponse> theatricalRoute(
      TheatricalHandler theatricalHandler,
      TheatricalSearchHandler theatricalSearchHandler,
      TheatricalDeleteHandler theatricalRemoveHandler) {
    return RouterFunctions.route(
            POST("/v1/defineShows").and(acceptMediaType()), theatricalHandler::postData)
        .andRoute(
            DELETE("v1/deleteById/{doc_id}").and(acceptMediaType()),
            theatricalRemoveHandler::deleteById)
        .andRoute(
            DELETE("v1/deleteByDateAndTheater").and(acceptMediaType()),
            theatricalRemoveHandler::deleteByDateAndTheater)
        /*   .andRoute(
        DELETE("v1/deleteByDateAndCompany")
            .and(acceptMediaType()), theatricalRemoveHandler::deleteByDateAndCompany)*/
        .andRoute(
            GET("v1/findById/{doc_id}").and(acceptMediaType()), theatricalSearchHandler::findById)
        .andRoute(
            GET("v1/findByDateAndTheater").and(acceptMediaType()), theatricalSearchHandler::findByDateAndTheater);
        /*.andRoute(
            GET("v1/deleteByDateAndCompany").and(acceptMediaType()), theatricalSearchHandler::findByDateAndCompany);*/

    // .andRoute(PATCH("").and(acceptMediaType()),theatricalHandler::updateData);
  }

  private static RequestPredicate acceptMediaType() {
    return accept(MediaType.APPLICATION_JSON);
  }
}
