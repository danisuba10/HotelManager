# Hotel Management System

[https://www.youtube.com/watch?v=dQw4w9WgXcQ&t](URL)

## Description

A Java backend for managing hotels, created using the Spring Boot framework.<br><br>
Its main purpose is to return all the hotels nearby to the user's location withing a specific radius. After which the user can choose a hotel, for which all free rooms are returned for the given timeframe.<br><br>
The rooms can get reserved, the reservation can be cancelled if the user asks for that more than 2 hours before the start date of the reservation.
<br><br>
After cancelling a room, reviews can be added, which are helpful when checking reviews of a specific hotel

## Stack used
- **Spring Boot Java framework**
- **Oracle MySQL**

## Backend

## APIs

### Get Nearby Hotels
- **Endpoint**: `/hotels`
- **Method**: `GET`
- **Params**:
  - `lat` (float): User latitude
  - `lon` (float): User longitude
  - `radius` (int): Search radius in kilometers
- **Response**: A list of nearby hotels
- **Example Request**:
`GET /hotels?lat=46.7730202&lon=23.6208637&radius=5`

### Get Hotel Rooms
- **Endpoint**: `/rooms`
- **Method**: `GET`
- **Params**:
- `hotelID` (int): ID of the hotel
- `start` (LocalDateTime): Start date and time
- `end` (LocalDateTime): End date and time
- **Response**: A list of available rooms for the specified hotel and date range
- **Example Request**:
`GET /rooms?hotelID=1&start=2024-05-01T01:01:01&end=2024-05-02T01:01:01`

### Reserve Room
- **Endpoint**: `/reserve`
- **Method**: `POST`
- **Params**:
- `roomID` (int): ID of the room to reserve
- `start` (LocalDateTime): Start date and time
- `end` (LocalDateTime): End date and time
- **Response**: Confirmation message of reservation attempt
- **Example Request**:
`POST /reserve?roomID=1&start=2024-05-01T01:01:01&end=2024-05-02T01:01:01`

### Cancel Reservation
- **Endpoint**: `/cancel`
- **Method**: `POST`
- **Params**:
- `reservationID` (int): ID of the reservation to cancel
- **Response**: Success or error message depending on cancellation status
- **Example Request**:
`POST /cancel?reservationID=3`

### Add Review
- **Endpoint**: `/review/add`
- **Method**: `POST`
- **Params**:
- `roomID` (int): ID of the room
- `points` (int): Rating points
- `reviewText` (string): Review text
- **Response**: Confirmation message of review addition
- **Example Request**:
`GET /review/add?roomID=1&points=5&reviewText=Excellent%20service`

### Get Reviews
- **Endpoint**: `/review/get`
- **Method**: `GET`
- **Params**:
- `hotelID` (int): ID of the hotel
- **Response**: A list of reviews for the specified hotel
- **Example Request**:
`GET /review/get?hotelID=1`

### Get All Reservations
- **Endpoint**: `/reserve/getall`
- **Method**: `GET`
- **Response**: A list of all reservations
- **Example Request**:
`GET /reserve/getall`