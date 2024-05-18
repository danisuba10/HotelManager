package com.hotelmanager.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private int roomID;
    private int hotelID;
    private int points;
    private String reviewText;

    public Review()
    {
        this.roomID = 0;
        this.hotelID = 0;
        this.points = 0;
        this.reviewText = "";
    }

    public Review(int roomID, int hotelID, int points, String reviewText)
    {
        this.roomID = roomID;
        this.hotelID = hotelID;
        this.points = points;
        this.reviewText = reviewText;
    }

    public int getId()
    {
        return id;
    }

    public int getRoomID()
    {
        return roomID;
    }

    public void setRoomID(int roomID)
    {
        this.roomID = roomID;
    }

    public int getHotelID()
    {
        return hotelID;
    }

    public void setHotelID(int hotelID)
    {
        this.hotelID = hotelID;
    }

    public int getPoints()
    {
        return points;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }

    public String getReviewText()
    {
        return reviewText;
    }

    public void setReviewText(String reviewText)
    {
        this.reviewText = reviewText;
    }

}
