package com.fuber.restbackend.api.rest;

import com.fuber.restbackend.data.DataService;
import com.fuber.restbackend.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class Users {

    private final DataService dataService;

    public Users(DataService dataService) {
        this.dataService = dataService;
    }

    @RequestMapping("")
    public List<User> getUsers() {
        return dataService.getUserList();
    }

}
