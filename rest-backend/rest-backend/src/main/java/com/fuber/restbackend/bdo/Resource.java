package com.fuber.restbackend.bdo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
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
        this.ownerships = new ArrayList<>();
        this.percentAvailable = 100;
        this.rentals = new ArrayList<>();
    }

    public void addOwnership(Ownership ownership) {
        if(percentAvailable - ownership.percentage < 0){
            throw new IllegalStateException("Total asset ownership can't exceed 100%");
        }
        this.percentAvailable -= ownership.percentage;
        this.ownerships.add(ownership);
    }

    public void addRental(Rental rental) {
        this.rentals.add(rental);
    }
}
