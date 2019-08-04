package com.fuber.restbackend.utils;

import com.fuber.restbackend.bod.Rental;
import com.fuber.restbackend.bod.Resource;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Filters {

    public static List<Resource> getResourcesAvailableToPurchase(Collection<Resource> resources) {
        Predicate<Resource> byAvailability = resource -> resource.getPercentAvailable() > 0;
        return resources.stream().filter(byAvailability).collect(Collectors.toList());
    }

    public static List<Resource> resourcesByType(Collection<Resource> resources, String type) {

        Predicate<Resource> byType = resource -> resource.getType().equals(type);

        return resources.stream()
                .filter(byType)
                .collect(Collectors.toList());
    }

    public static List<Resource> resourcesById(Collection<Resource> resources, int id) {

        Predicate<Resource> byType = resource -> resource.getId() == id;

        return resources.stream()
                .filter(byType)
                .collect(Collectors.toList());
    }

    public static List<Rental> rentalsByResourceId(Collection<Rental> rentals, int resourceId) {

        Predicate<Rental> byResourceId = rental -> rental.getResourceId() == resourceId;

        return rentals.stream()
                .filter(byResourceId)
                .collect(Collectors.toList());
    }

    public static List<Resource> resourcesAvailableByDate(Collection<Resource> resources, DateTime startDate, DateTime endDate){
        //TODO Use streams
        List<Resource> availableResources = new ArrayList<>();
        for(Resource resource : resources) {
            if(resource.isAvailable(startDate, endDate)){
                availableResources.add(resource);
            }
        }
        return availableResources;
    }

}
