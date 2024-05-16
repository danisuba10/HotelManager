package com.hotelmanager.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Entity
public class Hotel {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private float latitude;
    private float longitude;
    @Transient
    private HashSet<Integer> roomIDS;
    @Transient
    @ManyToMany(mappedBy = "hotels")
    private HashSet<Room> rooms;

    public Hotel(int id, String name, float latitude, float longitude, HashSet<Integer> roomIDS, HashSet<Room> rooms)
    {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.roomIDS = roomIDS;
        this.rooms = rooms;
    }

    public Hotel() {
        Random rand = new Random();
        this.id = rand.nextInt(Integer.MAX_VALUE);
        this.name = "";
        this.latitude = 0f;
        this.longitude = 0f;
        this.rooms = new HashSet<>();
        this.roomIDS = new HashSet<>();
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public float getLatitude()
    {
        return latitude;
    }

    public void setLatitude(float latitude)
    {
        this.latitude = latitude;
    }

    public float getLongitude(){
        return longitude;
    }

    public void setLongitude(float longitude)
    {
        this.longitude = longitude;
    }

    public Set<Room> getRooms(){
        return rooms;
    }

    public void setRooms(HashSet<Room> rooms)
    {
        this.rooms = rooms;
    }

    public Set<Integer> getRoomIds(){
        return this.roomIDS;
    }

    public void setRoomIds(HashSet<Integer> roomIDS)
    {
        this.roomIDS = roomIDS;
    }

}
