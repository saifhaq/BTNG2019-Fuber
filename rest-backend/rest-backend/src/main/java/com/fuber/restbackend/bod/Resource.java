package com.fuber.restbackend.bod;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class Resource {

    private int id;
    private String type;
    private double percentAvailable;
    private List<Ownership> ownerships;
    private List<Rental> rentals;

    public Resource(int id, String type) {
        this.id = id;
        this.type = type;
        this.ownerships = new ArrayList<>();
        this.percentAvailable = 100;
        this.rentals = new ArrayList<>();
    }

    public boolean purchasePossible() {
        return percentAvailable > 0;
    }

    public boolean percentageIsAvailable(double purchasePercentage){
        return (percentAvailable - purchasePercentage) > 0;
    }

    public boolean isAvailable(DateTime startDate, DateTime endDate){
        for(Rental currentRental : this.rentals) {
            if ((startDate.getMillis() < currentRental.getEndDate().getMillis()) && endDate.isAfter(currentRental.getStartDate())) {
                return false;
            }
        }
        return true;
    }

    public void addOwnership(Ownership ownership) {
        this.ownerships.add(ownership);
        this.percentAvailable -= ownership.percentage;
    }

}
