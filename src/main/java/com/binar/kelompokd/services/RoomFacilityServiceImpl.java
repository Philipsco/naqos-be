package com.binar.kelompokd.services;

import com.binar.kelompokd.models.entity.RoomFacility;
import com.binar.kelompokd.models.request.RoomFacilityRequest;
import com.binar.kelompokd.repos.RoomFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RoomFacilityServiceImpl implements RoomFacilityService{

    @Autowired
    RoomFacilityRepository roomFacilityRepository;

    @Override
    @Transactional
    public RoomFacility addFacility(RoomFacilityRequest roomFacilityRequest) {

        RoomFacility roomFacility = RoomFacility.builder()
                .name(roomFacilityRequest.getName())
                .condition(roomFacilityRequest.getCondition())
                .isActive(false)
                .build();

        return roomFacilityRepository.save(roomFacility);
    }

    @Override
    @Transactional
    public String editFacility(Integer id, RoomFacilityRequest roomFacilityRequest) {

        RoomFacility roomFacility = roomFacilityRepository.findById(id).get();

        roomFacility.setName(roomFacilityRequest.getName());
        roomFacility.setCondition(roomFacilityRequest.getCondition());

        roomFacilityRepository.save(roomFacility);

        return "Room facility updated successfully";
    }

    @Override
    @Transactional
    public String deleteFacility(Integer id) {
        roomFacilityRepository.deleteById(id);

        return "Room facility deleted successfully";
    }
}
