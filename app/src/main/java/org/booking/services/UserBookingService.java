package org.booking.services;
import java.util.*;

import java.io.File;
import java.io.IOException;

import org.booking.entities.User;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserBookingService {

    private User user;
    private List<User> usersList;

    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String USERS_PATH ="../localDb/Users.json";

    public UserBookingService(User user) throws JsonParseException, JsonMappingException, IOException{
        this.user = user;
        File users = new File(USERS_PATH);
        usersList = objectMapper.readValue(users, new TypeReference<List<User>>() {});

    }
}
