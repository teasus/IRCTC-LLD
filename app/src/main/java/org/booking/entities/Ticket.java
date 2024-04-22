package org.booking.entities;

import java.sql.Date;


public class Ticket {

    private String ticketId;
    private String source;

    private String destination;
    private Date dateOfTravel;
    private Train train;


    public Ticket(String ticketId, String source, String destination, Date dateOfTravel, Train train,  String userId) {
        this.ticketId = ticketId;
        this.source = source;
        this.destination = destination;
        this.dateOfTravel = dateOfTravel;
        this.train = train;

        this.userId = userId;
    }


    private String userId;
    public String getTicketInfo(){
        return String.format("Ticket ID: %s belongs to User %s from %s to %s on %s", ticketId, userId, source, destination, dateOfTravel);
    }


    public Ticket(String ticketId, String source, String userID, String destination, Date dateOfTravel, Train train) {
        this.ticketId = ticketId;
        this.source = source;
        this.userId = userId;
        this.destination = destination;
        this.dateOfTravel = dateOfTravel;
        this.train = train;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUserId() {
        return userId;
    }

    public Ticket() {
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDateOfTravel() {
        return dateOfTravel;
    }

    public void setDateOfTravel(Date dateOfTravel) {
        this.dateOfTravel = dateOfTravel;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }




}



