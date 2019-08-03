package com.fuber.restbackend.api.rest;

import com.fuber.restbackend.bdo.User;
import com.fuber.restbackend.data.DataService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserApi {

    private final DataService dataService;

    public UserApi(DataService dataService) {
        this.dataService = dataService;
    }

    @RequestMapping("")
    public Collection<User> getUsers() {
        return dataService.getUserList().values();
    }

}
