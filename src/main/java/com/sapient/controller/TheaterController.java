package com.sapient.controller;


import com.sapient.model.Theatre;
import com.sapient.service.impl.TheatricalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/theatres")
public class TheaterController {



    @Autowired
    private TheatricalServiceImpl service;


    @PostMapping("/defineShows")
    public Mono<Theatre> listAllHotels(@RequestBody Theatre theaterList) {
        return  service.saveData(theaterList);
    }

    }

