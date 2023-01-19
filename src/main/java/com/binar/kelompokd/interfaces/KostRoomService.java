package com.binar.kelompokd.interfaces;

import com.binar.kelompokd.models.entity.kost.KostRoom;
import com.binar.kelompokd.models.request.KostRoomRequest;

import java.util.List;
import java.util.UUID;

public interface KostRoomService {
    KostRoom addRoom(KostRoomRequest kostRoomRequest);
    KostRoom updateRoom(UUID id, KostRoomRequest kostRoomRequest);
    String deleteRoom(UUID id);

    List<KostRoom> getAllRooms();

    KostRoom getRoomById(UUID id);

    String softDeleteRoom(UUID id);
}
