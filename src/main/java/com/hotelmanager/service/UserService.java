package com.hotelmanager.service;

import com.hotelmanager.domain.Hotel;
import com.hotelmanager.domain.Reservation;
import com.hotelmanager.domain.Review;
import com.hotelmanager.domain.Room;
import com.hotelmanager.exception.ReservationCancelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    HotelService hotelService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    RoomService roomService;
    @Autowired
    private ReviewService reviewService;

    public List<Hotel> inRangeHotels(float userLatitude, float userLongitude, int radius) {
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

    public List<Room> availableRooms(int hotelID, LocalDateTime reservationStartTime, LocalDateTime reservationEndTime)
    {
        return roomService.findAvailableByHotelId(hotelID, reservationStartTime, reservationEndTime);
    }

    public void reserve(int roomID, LocalDateTime reservationStartTime, LocalDateTime reservationEndTime)
    {
        reservationService.reserve(roomID, reservationStartTime, reservationEndTime);
    }

    public void cancel(int reservationID) throws ReservationCancelException {
        reservationService.cancel(reservationID);
    }

    public void addReview(int roomID, int points, String reviewText)
    {
        Hotel hotel = hotelService.findByRoom(roomID);
        reviewService.add(roomID, hotel.getId(), points, reviewText);
    }

    public List<Review> getReviewsForHotel(int hotelID)
    {
        return reviewService.getReviewsForHotel(hotelID);
    }

    public List<Reservation> getReservations(){
        return this.reservationService.getReservations();
    }

    public void test_distanceFilter()
    {
        float userLat, userLong;
        userLat = 46.7730202f;
        userLong = 23.6208637f;
        List<Hotel> hotels = this.inRangeHotels(userLat, userLong, 3);
        int a = 0;
    }

    public void test_review()
    {
        this.addReview(1, 8, "Mediocre");
    }
}
