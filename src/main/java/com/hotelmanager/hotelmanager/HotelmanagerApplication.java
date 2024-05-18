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
@ComponentScan(basePackages = {"com.hotelmanager.service", "com.hotelmanager.repo", "com.hotelmanager.controller"})
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

	}
}
