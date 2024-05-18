package com.hotelmanager.repo;

import com.hotelmanager.domain.Hotel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends CrudRepository<Hotel,Integer>
{

    @Query(
            "SELECT hotel FROM Hotel hotel JOIN RoomHotelRelation rhl on hotel.id = rhl.hotelID " +
                    "WHERE rhl.hotelRoomID = :roomID"
    )
    public Hotel getHotelByRoom(int roomID);

    public Long countHotelByNameAndLatitudeAndLongitude(String name, float lat, float lon);
}
