package com.sapient.service.impl;

import com.sapient.exceptions.DocumentNotFoundException;
import com.sapient.model.Theatre;
import com.sapient.repository.TheatreRepository;
import com.sapient.service.TheatricalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.function.Function;

@Service
@Slf4j
public class TheatricalServiceImpl implements TheatricalService {

  private final TheatreRepository reactiveRepo;

  @Autowired
  public TheatricalServiceImpl(TheatreRepository repo) {

    this.reactiveRepo = repo;
  }

  @Override
  public Mono<Theatre> saveData(Theatre theatreShow) {

    return reactiveRepo.save(theatreShow);
  }

  @Override
  public Mono<Void> deleteById(String id) {
    return reactiveRepo.deleteById(id).onErrorResume(handleError());
  }

  @Override
  public Mono<Void> deleteByDateAndTheater(LocalDate date, String theater) {
    return reactiveRepo.deleteByDateAndTheaterName(date, theater).onErrorResume(handleError());
  }

  @Override
  public Flux<Void> deleteByDateAndCompany(LocalDate date, String company) {
    return reactiveRepo.deleteByDateAndCompany(date, company);
  }

  @Override
  public Mono<Theatre> findById(String id) {
    return reactiveRepo.findById(id);
  }

  @Override
  public Mono<Theatre> findByDateAndTheater(LocalDate date, String theater) {
    return reactiveRepo.findByDateAndTheaterName(date, theater);
  }

  @Override
  public Flux<Theatre> findByDateAndCompany(LocalDate date, String company) {
    return reactiveRepo.findByDateAndCompany(date, company);
  }

  private static Function<Throwable, Mono<? extends Void>> handleError() {

    return err -> {
      log.info("There is error. need to manage it: {}", err.getMessage());
      if (err.getMessage().contains("Not Found")) {
        return Mono.error(new DocumentNotFoundException(err.getMessage()));
      } else {
        throw new RuntimeException(err.getMessage());
      }
    };
  }
}
