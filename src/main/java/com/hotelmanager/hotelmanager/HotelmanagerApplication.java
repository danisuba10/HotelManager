package com.hotelmanager.hotelmanager;

import com.hotelmanager.domain.Hotel;
import com.hotelmanager.domain.Room;
import com.hotelmanager.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cglib.core.Local;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@ComponentScan(basePackages = {"com.hotelmanager.service", "com.hotelmanager.repo"})
@EntityScan(basePackages = {"com.hotelmanager.domain"})
@EnableJpaRepositories(basePackages = {"com.hotelmanager.repo"})
public class HotelmanagerApplication {

	public static void main(String[] args)
	{
		ConfigurableApplicationContext context = SpringApplication.run(HotelmanagerApplication.class, args);
		HotelService hotelService = context.getBean(HotelService.class);
		RoomService roomService = context.getBean(RoomService.class);
		RoomHotelRelationService  roomHotelRelationService = context.getBean(RoomHotelRelationService.class);
		UserService userService = context.getBean(UserService.class);
		ReservationService reservationService = context.getBean(ReservationService.class);

		ServicesSingleton servicesSingleton = ServicesSingleton.getInstance(hotelService, roomService, roomHotelRelationService);

		jsonService jsonService = new jsonService(servicesSingleton);
		jsonService.read("src/main/resources/input.json");

		Optional<Hotel> hotel = hotelService.findById(1);
		List<Room> room = roomService.findByHotelID(3);
		reservationService.reserve(1, LocalDateTime.now().minusDays(2), LocalDateTime.now().plusDays(2));
		reservationService.reserve(2, LocalDateTime.now(), LocalDateTime.now().plusMinutes(60));
		List<Room> availableRooms = roomService.findAvailableByHotelId(hotel.get().getId(), LocalDateTime.now(), LocalDateTime.now().plusDays(1));
		reservationService.cancel(1);
		reservationService.cancel(2);
		availableRooms = roomService.findAvailableByHotelId(hotel.get().getId(), LocalDateTime.now(), LocalDateTime.now().plusDays(1));
		float userLat, userLong;
		userLat = 46.7730202f;
		userLong = 23.6208637f;
		int distance = DistanceService.distance(userLat, userLong, hotel.get().getLatitude(), hotel.get().getLongitude());
		int a = 0;
		userService.test_distanceFilter();
	}



}
