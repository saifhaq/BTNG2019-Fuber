package com.fuber.restbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Resource {

    private int id;
    private String type;
    private List<Ownership> ownerships;
    private float percentAvailable;
    private List<Rental> rentals;

    public Resource(int id, String type) {
        this.id = id;
        this.type = type;
        this.percentAvailable = 100;
    }

    public void addOwnership(Ownership ownership) {
        if(percentAvailable + ownership.percentage > 100){
            throw new IllegalStateException("Total asset ownership can't exceed 100%");
        }
        this.percentAvailable -= ownership.percentage;
        this.ownerships.add(ownership);
    }
}
