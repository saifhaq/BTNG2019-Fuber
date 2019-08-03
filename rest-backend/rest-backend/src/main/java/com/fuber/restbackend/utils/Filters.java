package com.fuber.restbackend.utils;

import com.fuber.restbackend.bdo.Rental;
import com.fuber.restbackend.bdo.Resource;
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

    public static List<Resource> resourcesAvailableByDate(Collection<Resource> resources, DateTime startDate, DateTime endDate){
        //TODO Use streams
        List<Resource> availableResources = new ArrayList<>();
        for(Resource resource : resources) {
            for(Rental currentRental : resource.getRentals()){
                if(startDate.isBefore(currentRental.getTo()) && endDate.isBefore(currentRental.getFrom())){
                    continue;
                }
                availableResources.add(resource);
                break;
            }
        }
        return availableResources;
    }

}
