package com.binar.kelompokd.interfaces;

import com.binar.kelompokd.models.entity.kost.Room;
import com.binar.kelompokd.models.request.RoomRequest;

import java.util.List;
import java.util.UUID;

public interface RoomService {
    Room addRoom(RoomRequest roomRequest);
    Room updateRoom(UUID id, Room room);
    String deleteRoom(UUID id);

    List<Room> getAllRooms();

    Room getRoomById(UUID id);

    String softDeleteRoom(UUID id);
}
