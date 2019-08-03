package com.fuber.restbackend.data;

import com.fuber.restbackend.dto.Ownership;
import com.fuber.restbackend.dto.Rental;
import com.fuber.restbackend.dto.Resource;
import com.fuber.restbackend.dto.User;
import lombok.Getter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Getter
@Component()
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DataService {

    private List<Ownership> ownershipList;
    private List<Rental> rentalList;
    private List<Resource> resourceList;
    private List<User> userList;
    private List<String> resourceTypes;

    public DataService() {
        this.ownershipList = new ArrayList<>();
        this.rentalList = new ArrayList<>();
        this.resourceList = new ArrayList<>();
        this.userList = new ArrayList<>();
        this.resourceTypes = new ArrayList<>();
        this.initialiseMockData();
    }

    public void addResource(Resource resource) {
        if(!this.resourceTypes.contains(resource.getType())){
            resourceTypes.add(resource.getType());
        }
        resourceList.add(resource);
    }

    private void initialiseMockData() {
        userList.add(new User(0,
                false,
                true,
                "test",
                "test",
                "0000"));
        userList.add(new User(0,
                false,
                true,
                "test",
                "test",
                "0000"));
        userList.add(new User(0,
                false,
                true,
                "test",
                "test",
                "0000"));

        //addResource(new Resource(0, "Tractor"));
        //addResource(new Resource(1, "Harvester"));

    }
}
