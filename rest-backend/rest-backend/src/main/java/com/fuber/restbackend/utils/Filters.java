package com.fuber.restbackend.utils;

import com.fuber.restbackend.dto.Rental;
import com.fuber.restbackend.dto.Resource;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Filters {

    public static List<Resource> resourcesByType(List<Resource> resources, String type) {

        Predicate<Resource> byType = resource -> resource.getType().equals(type);

        return resources.stream()
                .filter(byType)
                .collect(Collectors.toList());
    }

    public static List<Resource> resourcesAvailableByDate(List<Resource> resources, DateTime startDate, DateTime endDate){
        //TODO Use streams
        List<Resource> availableResources = new ArrayList<>();
        for(Resource resource : resources) {
            for(Rental currentRental : resource.getRentals()){
                if(startDate.isBefore(currentRental.getEnd_date()) && endDate.isBefore(currentRental.getStart_date())){
                    continue;
                }
                availableResources.add(resource);
                break;
            }
        }
        return availableResources;
    }

}
