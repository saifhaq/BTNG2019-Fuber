package com.fuber.restbackend.api.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

@Getter
@Setter
@NoArgsConstructor
public class RawBasicRental {

    int resource_id;
    String from;
    String to;

}
