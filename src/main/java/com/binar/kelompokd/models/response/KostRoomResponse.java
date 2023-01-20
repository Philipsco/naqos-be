package com.binar.kelompokd.models.response;

import com.binar.kelompokd.models.entity.kost.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KostRoomResponse {
    private List<Room> room;
}
