package com.sapient.repository;

import com.sapient.model.Theatre;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Repository
public interface TheatreRepository extends ReactiveMongoRepository<Theatre, String> {

  @Query("{ 'date': ?0, 'theaterName': ?1}")
  Mono<Theatre> findByDateAndTheaterName(LocalDate date, String theaterName);

  @Query("{ 'date': ?0, 'company': ?1}")
  Flux<Theatre> findByDateAndCompany(LocalDate date, String company);

  @DeleteQuery("{ 'date': ?0, 'company': ?1}")
  Flux<Void> deleteByDateAndCompany(LocalDate date, String company);

  @DeleteQuery("{ 'date': ?0, 'theaterName': ?1}")
  Mono<Void> deleteByDateAndTheaterName(LocalDate date, String theaterName);
}
