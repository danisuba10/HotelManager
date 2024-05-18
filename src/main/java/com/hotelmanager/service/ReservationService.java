package com.hotelmanager.service;

import com.hotelmanager.domain.Reservation;
import com.hotelmanager.exception.ReservationCancelException;
import com.hotelmanager.repo.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    public List<Reservation> getReservations(){
        List<Reservation> result = new ArrayList<>();
        Iterable<Reservation> iter = reservationRepository.findAll();
        for(Reservation reservation : iter)
        {
            result.add(reservation);
        }
        return result;
    }

    public void reserve(int roomID, LocalDateTime startDate, LocalDateTime endDate)
    {
        Reservation reservation = new Reservation(roomID, startDate, endDate);
        reservationRepository.save(reservation);
    }

    public void cancel(int reservationID) throws ReservationCancelException {
        Reservation reservation = reservationRepository.findById(reservationID).get();
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime endDate = reservation.getEndDate();
        Duration duration = Duration.between(currentTime, endDate);
        Duration twoHours = Duration.ofHours(2);

        if(duration.compareTo(twoHours) >= 0)
        {
            reservationRepository.deleteById(reservationID);
        }
        else
        {
            throw new ReservationCancelException("Can't cancel reservation. Reservation starts in < 2 hours!");
        }
    }

    private static boolean doesNotIntersect(LocalDateTime aStartDate, LocalDateTime aEndDate,
                                     LocalDateTime bStartDate, LocalDateTime bEndDate)
    {

        return aStartDate.isAfter(bEndDate) || aEndDate.isBefore(bStartDate);
    }

    public boolean isAvailable(int roomID, LocalDateTime startDate, LocalDateTime endDate)
    {
        List<Reservation> reservations = reservationRepository.findReservationsByRoomID(roomID);
        for(Reservation reservation : reservations)
        {
            if(!doesNotIntersect(startDate, endDate, reservation.getStartDate(), reservation.getEndDate()))
            {
                return false;
            }
        }
        return true;
    }
}
