package com.hotelmanager.service;

import com.hotelmanager.domain.Hotel;
import com.hotelmanager.domain.Room;
import com.hotelmanager.domain.RoomHotelRelation;
import com.hotelmanager.repo.RoomHotelRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomHotelRelationService {
    @Autowired
    private RoomHotelRelationRepository roomRepository;
    @Autowired
    private RoomHotelRelationRepository roomHotelRelationRepository;

    public void add(Hotel hotel, Room room) {
        RoomHotelRelation roomHotelRelation = new RoomHotelRelation(room.getId(), hotel.getId());
        roomRepository.save(roomHotelRelation);
    }

    public boolean existsByHotelIDAndRoomNumber(int hotelID, int roomNumber)
    {
        Long count =  roomHotelRelationRepository.existsByHotelIDAndRoomNumber(hotelID, roomNumber);
        if(count == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
