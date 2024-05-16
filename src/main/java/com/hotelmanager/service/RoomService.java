package com.hotelmanager.service;

import com.hotelmanager.domain.Room;
import com.hotelmanager.repo.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public void add(Room room) {
        roomRepository.save(room);
    }

    public List<Room> findByHotelID(int hotelID)
    {
        //return new ArrayList<>();
        return roomRepository.findByHotelId(hotelID);
    }

}
