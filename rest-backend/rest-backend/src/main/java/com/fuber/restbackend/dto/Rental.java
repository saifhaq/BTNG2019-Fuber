package com.fuber.restbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.joda.time.DateTime;

@Getter
@AllArgsConstructor
public class Rental {

    int id;
    int resource_id;
    int user_id;
    DateTime start_date;
    DateTime end_date;
    float price_per_day;

}
