package com.hotelmanager.domain;

import jakarta.persistence.*;

import java.io.Serializable;

/*@Entity
@IdClass(RoomHotelRelationId.class)
public class RoomHotelRelation {
    @Id
    @ManyToOne
    @JoinColumn(name = "room_id") // Adjusted to match the RoomHotelRelationId class
    private Room room;

    @Id
    @ManyToOne
    @JoinColumn(name = "hotel_id") // Adjusted to match the RoomHotelRelationId class
    private Hotel hotel;

    public RoomHotelRelation() {}

    public RoomHotelRelation(Hotel hotel, Room room) {
        this.hotel = hotel;
        this.room = room;
    }

    public Hotel getHotel()
    {
        return hotel;
    }

    public void setHotel(Hotel hotel)
    {
        this.hotel = hotel;
    }

    public Room getRoom()
    {
        return room;
    }

    public void setRoom(Room room)
    {
        this.room = room;
    }
}*/


@Entity
public class RoomHotelRelation {
    @Id
    private int hotelRoomID;
    private int hotelID;

    public RoomHotelRelation(int hotelRoomID, int hotelID)
    {
        this.hotelRoomID = hotelRoomID;
        this.hotelID = hotelID;
    }

    public RoomHotelRelation() {
        hotelID = 0;
        hotelRoomID = 0;
    }

    public int getHotelRoomID()
    {
        return hotelRoomID;
    }

    public void setHotelRoomID(int hotelRoomID)
    {
        this.hotelRoomID = hotelRoomID;
    }

    public int getHotelID()
    {
        return hotelID;
    }

    public void setHotelID(int hotelID)
    {
        this.hotelID = hotelID;
    }
}
