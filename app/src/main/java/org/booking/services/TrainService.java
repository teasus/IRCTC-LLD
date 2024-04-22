package org.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.booking.entities.Train;
import org.booking.entities.User;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class TrainService {

    private ObjectMapper objectMapper = new ObjectMapper();

    private List<Train> trainsList;

    public List<Train> getTrainsList() {
        return trainsList;
    }

    private static final String TRAIN_PATH = "./app/src/main/java/org/booking/localDb/trains.json";





    public List<Train> getTrainsList(String source,String destination) {
        System.out.println(trainsList.get(0).getStations());
        return trainsList
                .stream()
                .filter(train ->
                        train.getStations()
                                .stream()
                                .map(String::toLowerCase).anyMatch(stations -> stations.equalsIgnoreCase(source) && stations.equalsIgnoreCase(destination))).collect(Collectors.toList());
    }

    public void setTrainsList(List<Train> trainsList) {
        this.trainsList = trainsList;
    }



    public TrainService() throws IOException {
        trainsList = readData();
    }

    private List<Train> readData() throws IOException {
        File fs = new File(TRAIN_PATH);
       // return objectMapper.readValue(users, new com.fasterxml.jackson.core.type.TypeReference<List<User>>() {});
        return objectMapper.readValue(fs,new TypeReference<List<Train>>() {});
    }

    private void writeData() throws  IOException{
        File fs = new File(TRAIN_PATH);

        objectMapper.writeValue(fs,trainsList);
    }




    
}
