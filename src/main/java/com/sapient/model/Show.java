package com.sapient.model;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
@ToString
@Builder
public class Show {

    private String movie_name;
    private String auditorium_name;
    private Integer auditorium_id;
    private List<ShowTime> showtimes;


}
