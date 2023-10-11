package com.sapient.service;

import com.sapient.model.Theatre;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface TheatricalService {


    public Mono<Theatre> saveData(Theatre theatreShows);

    /*public Mono<Theatre> replaceData(Theatre theatreShow);*/

    public Mono<Void> deleteById(String id);

    public Mono<Void> deleteByDateAndTheater(LocalDate date, String theater);

    public Flux<Void> deleteByDateAndCompany(LocalDate date, String company);

    public Mono<Theatre> findById(String id);

    public Mono<Theatre> findByDateAndTheater(LocalDate date, String theater);



    public Flux<Theatre> findByDateAndCompany(LocalDate date, String company);
}
