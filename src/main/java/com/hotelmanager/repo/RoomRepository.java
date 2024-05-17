package com.hotelmanager.repo;

import com.hotelmanager.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer>
{

    //List<Room> findByIsAvailable(boolean available);

    @Query(
            "SELECT room " +
                    "FROM Room room " +
                    "JOIN RoomHotelRelation relation ON room.id = relation.hotelRoomID " +
                    "WHERE relation.hotelID = :hotelId"
    )
    List<Room> findByHotelId(int hotelId);

    @Query(
            "SELECT room " +
                    "FROM Room room " +
                    "JOIN RoomHotelRelation relation ON room.id = relation.hotelRoomID " +
                    "WHERE relation.hotelID = :hotelId and room.isAvailable = :available"
    )
    List<Room> findByHotelIdAndAvailable(int hotelId, boolean available);
}