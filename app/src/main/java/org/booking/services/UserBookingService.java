package org.booking.services;

import java.util.*;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

import org.booking.Utils.UserServiceUtil;
import org.booking.entities.Train;
import org.booking.entities.User;

import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserBookingService {

    private  User user;
    private List<User> usersList;

    private TrainService trainService = new TrainService();

    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String USERS_PATH = "./app/src/main/java/org/booking/localDb/Users.json";

    public UserBookingService(User user) throws
            IOException {
        this.user = user;

        usersList = loadUsers();


    }
    public List<User> loadUsers() throws IOException {
        File users = new File(USERS_PATH);
        return objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }



    public UserBookingService() throws IOException {


        File users = new File(USERS_PATH);
       usersList = loadUsers();


    }

    public boolean login(User user){

        Optional<User> foundUser = usersList.stream().filter(e-> {
           return
                   e.getName().equals(user.getName())
                           &&
                           UserServiceUtil.checkPassword(user.getPassword(),e.getHashPassword());
        }).findFirst();
       if(foundUser.isPresent()){
           this.user = foundUser.get();
       }


        return  foundUser.isPresent();
    }

    public boolean signUp(User user){
        try {
            usersList.add(user);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch (Exception e){
            System.out.println(e);
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws  IOException {

        File userFile = new File(USERS_PATH);
        objectMapper.writeValue(userFile,usersList);
    }


    public void  fetchBooking(){
        user.printTickets();
    }

    public boolean cancelBooking(String ticketId){

       user.setTicketsBooked(
               user.getTicketsBooked()
                       .stream()
                       .filter(ticket ->
                                       !ticket.getTicketId().equals(ticketId))
                       .collect(Collectors.toList()));

       return true;

    }

    public List<Train> getTrains(String source,String destination){

        return trainService.getTrainsList(source,destination);
    };

    public boolean bookATrain(Train train , int row,int col){
        List<List<Integer>> tempSeat = train.getSeats();
        if(tempSeat.get(row).get(col)== 1){
            return  false;
        }
        tempSeat.get(row).set(col,1);
        train.setSeats(tempSeat);

        trainService.setTrainsList(trainService.getTrainsList().stream().map(traine-> {
            if( traine.getTrainId().equals(train.getTrainId()) ){
                traine.setSeats(tempSeat);
            }
            return traine;
        }).collect(Collectors.toList()));
        return  true;
    }


}
