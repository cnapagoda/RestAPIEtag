package com.chandana.rest.api;

import java.util.Date;
import java.util.UUID;

/**
 * Created by chandana on 9/18/16.
 */
public class DataStoreSimulator {

    private static User user;

    static {
        user = new User(1,"Chandana", "Napagoda");
    }

    public static User getUser(){
        return user;
    }

    public static User updateUser(){
        user.setLname(UUID.randomUUID().toString());
        user.setLastUpdateTime(new Date());
        return user;
    }


}
