package com.fuber.restbackend.bod;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Rental {

    int id;
    int resourceId;
    int userId;
    DateTime startDate;
    DateTime endDate;
    double pricePerDay;

}
