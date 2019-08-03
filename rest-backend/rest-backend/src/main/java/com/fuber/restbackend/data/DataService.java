package com.fuber.restbackend.data;

import com.fuber.restbackend.bdo.Ownership;
import com.fuber.restbackend.bdo.Rental;
import com.fuber.restbackend.bdo.Resource;
import com.fuber.restbackend.bdo.User;
import lombok.Getter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Component()
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DataService {

    private Map<Integer, Ownership> ownershipList;
    private Map<Integer, Rental> rentalList;
    private Map<Integer, Resource> resourceList;
    private Map<Integer, User> userList;
    private List<String> resourceTypes;

    private AtomicInteger id;

    public DataService() {
        this.ownershipList = new HashMap<>();
        this.rentalList = new HashMap<>();
        this.resourceList = new HashMap<>();
        this.userList = new HashMap<>();
        this.resourceTypes = new ArrayList<>();
        this.id = new AtomicInteger(0);
        this.initialiseMockData();
    }

    public void addResource(Resource resource) {
        if(!this.resourceTypes.contains(resource.getType())){
            resourceTypes.add(resource.getType());
        }
        resourceList.put(resource.getId(), resource);
    }

    public void purchaseResource(int id, float percentage) {
        resourceList.get(id).addOwnership(new Ownership(this.id.getAndIncrement(), id, percentage));
    }

    public void addRental(Rental rental) {
//        if(rental.getId() == Integer.MAX_VALUE){
//            rental.setId(this.id.getAndIncrement());
//        }
//        rentalList.put(rental.getId(), rental);
        resourceList.get(rental.getResource_id()).addRental(rental);
    }

    private void initialiseMockData() {
        userList.put(id.get(), new User(id.getAndIncrement(),
                false,
                true,
                "Zak_K_Bartlett",
                "56 Scrimshire Lane, ASHURST, BN44 7TB",
                "+447879209872"));
        userList.put(id.get(), new User(id.getAndIncrement(),
                true,
                false,
                "Poppy_S_Hodgson",
                "117 Prospect Hill, DRAYTON, TA10 3ZS",
                "+447916799266"));
        userList.put(id.get(), new User(id.getAndIncrement(),
                true,
                true,
                "Luca_C_Ingram",
                "51  Worthy Lane, MARTYR WORTHY, SO21 2PT",
                "+7847392163"));
        userList.put(id.get(), new User(id.getAndIncrement(),
                false,
                false,
                "Sarah_M_Myers",
                "50 Marlborough Crescent, SOUTHILL, SG18 5EQ",
                "+447902256322"));

        addResource(new Resource(id.getAndIncrement(), "Tractor"));
        addResource(new Resource(id.getAndIncrement(), "Harvester"));

    }
}
