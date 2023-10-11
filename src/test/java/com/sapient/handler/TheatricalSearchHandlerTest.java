package com.sapient.handler;

import com.sapient.model.Show;
import com.sapient.model.ShowTime;
import com.sapient.model.Theatre;
import com.sapient.service.impl.TheatricalServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.reactive.function.server.MockServerRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TheatricalSearchHandlerTest {

  @InjectMocks private TheatricalSearchHandler searchHandler;

  @Mock private TheatricalServiceImpl service;

  private final String HOSTNAME = "http://localhost:8082/";

  @Test
  public void findByIdTest() {

    Theatre theatre =
        Theatre.builder()
            .city("KOL")
            .address("Central Kol")
            .company("Nifty Group")
            .theaterName("Manas")
            .date(LocalDate.of(2023, 03, 10))
            .shows(
                List.of(
                    Show.builder()
                        .movie_name("Kuch Bhi")
                        .auditorium_id(10)
                        .showtimes(
                            List.of(
                                ShowTime.builder().start_time("12:00").end_time("15:00").build()))
                        .build()))
            .build();
    when(service.findById(anyString())).thenReturn(Mono.just(theatre));

    MockServerRequest serverRequestObj = getMockServerRequestObj();
    Mono<ServerResponse> findByIdResp = searchHandler.findById(serverRequestObj);

    StepVerifier.create(findByIdResp)
        .consumeNextWith(
            resp -> {
              assertEquals(HttpStatus.OK, resp.statusCode());
            })
        .verifyComplete();
  }

  private MockServerRequest getMockServerRequestObj() {

    MockServerHttpRequest request =
        MockServerHttpRequest.get(HOSTNAME + "v1/findById/ID01").build();
    HttpHeaders headers = new HttpHeaders();

    MockServerWebExchange exchange = MockServerWebExchange.from(request);

    MockServerRequest requestObj =
        MockServerRequest.builder().headers(headers).pathVariable("doc_id","ID)1").exchange(exchange).build();

    return requestObj;
  }
}
