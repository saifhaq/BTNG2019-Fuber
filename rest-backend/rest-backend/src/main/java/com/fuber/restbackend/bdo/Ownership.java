package com.fuber.restbackend.bdo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Ownership {

    int id;
    int resource_id;
    int user_id;
    float percentage;

    public Ownership(int id, int resource_id, float percentage){
        this.resource_id = resource_id;
        this.percentage = percentage;
        this.user_id = Integer.MAX_VALUE;
    }

}
