package com.fuber.restbackend.service;

import com.fuber.restbackend.api.rest.dto.RawBasicRental;
import com.fuber.restbackend.bod.Ownership;
import com.fuber.restbackend.bod.Rental;
import com.fuber.restbackend.bod.Resource;
import com.fuber.restbackend.bod.User;
import lombok.Getter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DataService {

    private Map<Integer, Ownership> ownershipList;
    private Map<Integer, Rental> rentalList;
    private Map<Integer, Resource> resourceList;
    private Map<Integer, User> userList;
    private List<String> resourceTypes;

    private AtomicInteger id;

    private final DateTimeFormatter formatter;

    @Autowired
    public DataService(DateTimeFormatter formatter) {
        this.ownershipList = new HashMap<>();
        this.rentalList = new HashMap<>();
        this.resourceList = new HashMap<>();
        this.userList = new HashMap<>();
        this.resourceTypes = new ArrayList<>();
        this.id = new AtomicInteger(0);
        this.formatter = formatter;
        this.initialiseMockData();
    }

    public void addResource(Resource resource) {
        if(!this.resourceTypes.contains(resource.getType())){
            resourceTypes.add(resource.getType());
        }
        resourceList.put(resource.getId(), resource);
    }

    public void purchaseResource(int id, double percentage) {
        resourceList.get(id).addOwnership(new Ownership(this.id.getAndIncrement(), id, 0, percentage));
    }

    public void addRental(Rental rental) {
        if(rental.getId() == Integer.MAX_VALUE){
            rental.setId(this.id.getAndIncrement());
        }
        rentalList.put(rental.getId(), rental);
        resourceList.get(rental.getResourceId()).getRentals().add(rental);
    }

    public void newRental(RawBasicRental rental) {
        Rental tmpRental = new Rental(id.getAndIncrement(),
                rental.getResource_id(),
                0, //Not implemented
                DateTime.parse(rental.getFrom(), formatter),
                DateTime.parse(rental.getTo(), formatter),
                0); //Not implemented
        this.rentalList.put(tmpRental.getId(), tmpRental);
        this.resourceList.get(rental.getResource_id()).getRentals().add(tmpRental);
        //this.userList.get().add(rental) Not implemented
    }

    private void initialiseMockData() {
        addResource(new Resource(id.getAndIncrement(), "Tractor")); //Id 0
        addResource(new Resource(id.getAndIncrement(), "Harvester")); //Id 1
        addResource(new Resource(id.getAndIncrement(), "Fertiliser")); //Id 2
        addResource(new Resource(id.getAndIncrement(), "Chemical")); //Id 3
        addResource(new Resource(id.getAndIncrement(), "Drones")); //Id 4
        addResource(new Resource(id.getAndIncrement(), "Conveyor")); //Id 5
        addResource(new Resource(id.getAndIncrement(), "Milling Machine")); //Id 6

        newRental(new RawBasicRental(0, "20190725", "20190925"));
        newRental(new RawBasicRental(0, "20191026", "20200116"));
        newRental(new RawBasicRental(0, "20180125", "20190312"));
    }
}
