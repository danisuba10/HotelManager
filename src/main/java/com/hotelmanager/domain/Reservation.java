package com.hotelmanager.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int reservationID;

    int roomID;

    LocalDateTime startDate;
    LocalDateTime endDate;

    public Reservation()
    {
        this.roomID = 0;
        this.startDate = LocalDateTime.of(1, 1, 1, 1, 1);
        this.endDate = LocalDateTime.of(1, 1, 1, 1, 1);
    }

    public Reservation(int roomID, LocalDateTime startDate, LocalDateTime endDate)
    {
        this.roomID = roomID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

   public int getReservationID()
   {
       return reservationID;
   }

   public int getRoomID()
   {
       return roomID;
   }

   public void setRoomID(int roomID)
   {
       this.roomID = roomID;
   }

   public LocalDateTime getStartDate()
   {
       return startDate;
   }

   public void setStartDate(LocalDateTime startDate)
   {
       this.startDate = startDate;
   }

   public LocalDateTime getEndDate()
   {
       return endDate;
   }

   public void setEndDate(LocalDateTime endDate)
   {
       this.endDate = endDate;
   }

}
