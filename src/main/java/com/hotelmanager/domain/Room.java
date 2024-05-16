package com.hotelmanager.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Entity
public class Room {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int roomNumber;
    private int type;
    private int price;
    private boolean isAvailable;

    @ManyToMany
    private Set<Hotel> hotels = new HashSet<>();

    public Room(int roomNumber, int type, int price)
    {
        Random rand = new Random();

        this.id = rand.nextInt(Integer.MAX_VALUE);
        this.price = price;
        this.roomNumber = roomNumber;
        this.type = type;
        this.isAvailable = true;
    }

    public Room() {
        Random rand = new Random();
        this.id = rand.nextInt(Integer.MAX_VALUE);
        this.roomNumber = 0;
        this.type = 0;
        this.isAvailable = false;
    }

    public int getId()
    {
        return id;
    }

    public void newID()
    {
        this.id = new Random().nextInt(Integer.MAX_VALUE);
    }

    public int getRoomNumber(){
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber){
        this.roomNumber = roomNumber;
    }

    public boolean getIsAvailable(){
        return this.isAvailable;
    }

    public void setIsAvailable(boolean value){
        this.isAvailable = value;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type){
        this.type = type;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public int getPrice()
    {
        return price;
    }

}
