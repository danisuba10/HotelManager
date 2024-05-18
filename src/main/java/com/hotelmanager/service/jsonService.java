package com.hotelmanager.service;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotelmanager.domain.Hotel;
import com.hotelmanager.domain.Room;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class jsonService {

    private final ObjectMapper objectMapper;
    private final ServicesSingleton servicesSingleton;

    public jsonService(ServicesSingleton singleton)
    {
        this.objectMapper = new ObjectMapper();
        servicesSingleton = singleton;
    }

    public void read(String filepath){

        try
        {
            Hotel[] hotels = objectMapper.readValue(new File(filepath), Hotel[].class);

            for(Hotel hotel : hotels)
            {
                this.servicesSingleton.getHotelService().add(hotel);
                for(Room room : hotel.getRooms())
                {
                    hotel.getRoomIds().add(room.getId());
                    if(this.servicesSingleton.getRoomHotelRelationService().existsByHotelIDAndRoomNumber(hotel.getId(), room.getRoomNumber()))
                    {
                        this.servicesSingleton.getRoomService().add(room);
                        this.servicesSingleton.getRoomHotelRelationService().add(hotel, room);
                    }
                }

            }
        } catch (StreamReadException e) {
            LogService.log(e.getMessage());
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            LogService.log(e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            LogService.log(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
