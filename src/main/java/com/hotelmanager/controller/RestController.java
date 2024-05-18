package com.hotelmanager.controller;

import com.hotelmanager.domain.Hotel;
import com.hotelmanager.domain.Reservation;
import com.hotelmanager.domain.Review;
import com.hotelmanager.domain.Room;
import com.hotelmanager.exception.ReservationCancelException;
import com.hotelmanager.service.ReservationService;
import com.hotelmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private UserService userService;
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/hotels")
    public ResponseEntity<List<Hotel>> nearbyHotels(@RequestParam float lat, @RequestParam float lon, @RequestParam int radius)
    {
        List<Hotel> nearbyHotels = userService.inRangeHotels(lat, lon, radius);
        return new ResponseEntity<>(nearbyHotels, HttpStatus.OK);
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<Room>> hotelRooms(@RequestParam int hotelID, @RequestParam LocalDateTime start, @RequestParam LocalDateTime end)
    {
        List<Room> freeRooms = userService.availableRooms(hotelID, start, end);
        return new ResponseEntity<>(freeRooms, HttpStatus.OK);
    }

    @PostMapping("/reserve")
    public ResponseEntity<String> reserveRoom(@RequestParam int roomID,
                                              @RequestParam LocalDateTime start, @RequestParam LocalDateTime end)
    {
        userService.reserve(roomID, start, end);
        return new ResponseEntity<>("Trying reserve for"+roomID, HttpStatus.OK);
    }

    @PostMapping("/cancel")
    public ResponseEntity<String> cancelReservation(@RequestParam int reservationID)
    {
        try {
            userService.cancel(reservationID);
            return new ResponseEntity<>("Succesfully canceled", HttpStatus.OK);
        } catch (ReservationCancelException e) {
            return new ResponseEntity<>("Can't cancel! There are less than 2 hours before start", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/review/add")
    public ResponseEntity<String> addReview(@RequestParam int roomID, @RequestParam int points,
                                            @RequestParam String reviewText)
    {
        userService.addReview(roomID, points, reviewText);
        return new ResponseEntity<>("Added review!", HttpStatus.OK);
    }

    @GetMapping("/review/get")
    public ResponseEntity<List<Review>> getReviews(@RequestParam int hotelID)
    {
        List<Review> reviews = userService.getReviewsForHotel(hotelID);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/reserve/getall")
    public ResponseEntity<List<Reservation>> getReviews()
    {
        List<Reservation> reservations = reservationService.getReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
}
