package com.hotelmanager.service;

import com.hotelmanager.domain.Room;
import com.hotelmanager.repo.ReservationRepository;
import com.hotelmanager.repo.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private ReservationService reservationService;

    public void add(Room room) {
        roomRepository.save(room);
    }

    public List<Room> findByHotelID(int hotelID)
    {
        //return new ArrayList<>();
        return roomRepository.findByHotelId(hotelID);
    }

    public List<Room> findAvailableByHotelId(int hotelID, LocalDateTime reservationStartDate, LocalDateTime reservationEndDate)
    {
        List<Room> result = new ArrayList<>();
        List<Room> rooms = roomRepository.findByHotelId(hotelID);
        for(Room room : rooms)
        {
            if(reservationService.isAvailable(room.getId(), reservationStartDate, reservationEndDate))
            {
                result.add(room);
            }
        }
        return result;
    }

}
