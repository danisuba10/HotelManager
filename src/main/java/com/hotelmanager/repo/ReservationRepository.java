package com.hotelmanager.repo;

import com.hotelmanager.domain.Reservation;
import org.springframework.data.repository.CrudRepository;

import javax.sql.rowset.CachedRowSet;
import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Integer>
{

    public List<Reservation> findReservationsByRoomID(int roomId);
}
