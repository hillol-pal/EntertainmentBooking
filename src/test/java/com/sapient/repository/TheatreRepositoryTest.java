package com.sapient.repository;

import com.sapient.model.Show;
import com.sapient.model.ShowTime;
import com.sapient.model.Theatre;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.List;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class TheatreRepositoryTest {

  @Autowired private TheatreRepository repo;

  @Test
  public void shouldSaveSingleTheatreShowsForADay() {
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
    Publisher<Theatre> setup = repo.deleteAll().thenMany(repo.save(theatre));
    StepVerifier.create(setup).expectNextCount(1).verifyComplete();
  }
}
