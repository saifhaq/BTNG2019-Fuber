package com.fuber.restbackend.bdo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {

    private int id;
    private boolean investor;
    private boolean renter;
    private String username;
    private String address;
    private String phone_number;

}
