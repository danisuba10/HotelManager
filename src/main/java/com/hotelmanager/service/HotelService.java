package com.hotelmanager.service;

import com.hotelmanager.domain.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import com.hotelmanager.repo.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public void add(Hotel hotel)
    {
        this.hotelRepository.save(hotel);
    }

    public Optional<Hotel> findById(int id){
        return this.hotelRepository.findById(id);
    }

    public List<Hotel> findAll()
    {
        List<Hotel> result = new ArrayList<Hotel>();
        Iterable<Hotel> iterHotels =  this.hotelRepository.findAll();
        for(Hotel hotel : iterHotels)
        {
            result.add(hotel);
        }
        return result;
    }

}
