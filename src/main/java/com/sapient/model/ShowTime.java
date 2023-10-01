package com.sapient.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@ToString
public class ShowTime {

    private String start_time;
    private String end_time;


}
