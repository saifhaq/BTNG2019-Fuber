package com.fuber.restbackend.bod;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Ownership {

    int id;
    int resource_id;
    int user_id;
    double percentage;

    public Ownership(int id, int resource_id, double percentage){
        this.resource_id = resource_id;
        this.percentage = percentage;
        this.user_id = Integer.MAX_VALUE;
    }

}
