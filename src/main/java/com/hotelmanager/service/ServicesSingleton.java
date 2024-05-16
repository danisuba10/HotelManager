package com.hotelmanager.service;

public class ServicesSingleton {
    private static ServicesSingleton instance;

    private final RoomService roomService;
    private final HotelService hotelService;
    private final RoomHotelRelationService roomHotelRelationService;

    private ServicesSingleton(HotelService hotelService, RoomService roomService, RoomHotelRelationService roomHotelRelationService)
    {
        this.hotelService = hotelService;
        this.roomService = roomService;
        this.roomHotelRelationService = roomHotelRelationService;
    }

    public static synchronized ServicesSingleton getInstance(HotelService hotelService, RoomService roomService, RoomHotelRelationService roomHotelRelationService)
    {
        if(instance == null) {
            instance = new ServicesSingleton(hotelService, roomService, roomHotelRelationService);
        }
        return instance;
    }

    public RoomService getRoomService()
    {
        return roomService;
    }

    public HotelService getHotelService()
    {
        return hotelService;
    }

    public RoomHotelRelationService getRoomHotelRelationService()
    {
        return roomHotelRelationService;
    }
}
