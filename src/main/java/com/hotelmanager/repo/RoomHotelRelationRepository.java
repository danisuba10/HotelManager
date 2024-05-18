package com.hotelmanager.repo;

import com.hotelmanager.domain.RoomHotelRelation;
import jdk.jfr.Percentage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RoomHotelRelationRepository extends CrudRepository<RoomHotelRelation, Integer>
{

    @Query("SELECT count(rhl) FROM RoomHotelRelation rhl JOIN Room room on " +
            "rhl.hotelRoomID = room.id WHERE rhl.hotelID = :hotelID and room.roomNumber = :roomNumber")
    long existsByHotelIDAndRoomNumber(@Param("hotelID") int hotelID, @Param("roomNumber") int roomNumber);
}
