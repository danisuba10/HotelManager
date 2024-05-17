package com.hotelmanager.service;

import com.hotelmanager.domain.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    HotelService hotelService;

    List<Hotel> inRangeHotels(float userLatitude, float userLongitude, int radius) {
        List<Hotel> result = new ArrayList<>();
        List<Hotel> hotels = hotelService.findAll();

        for (Hotel hotel : hotels)
        {
            if(DistanceService.distance(userLatitude, userLongitude, hotel.getLatitude(), hotel.getLongitude()) < radius)
            {
                result.add(hotel);
            }
        }

        return result;
    }

}
