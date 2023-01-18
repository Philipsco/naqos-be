package com.binar.kelompokd.controllers;

import com.binar.kelompokd.models.entity.Address;
import com.binar.kelompokd.models.entity.City;
import com.binar.kelompokd.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping()
    public ResponseEntity<?> getAllAddresses(){
        return new ResponseEntity<>(addressService.getAllAddresses(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(addressService.getAddressById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> saveAddress(@RequestBody Address address){
        return new ResponseEntity<>(addressService.save(address), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable("id") Integer id, @RequestBody Address address){
        return new ResponseEntity<>(addressService.update(id, address), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable("id") Integer id){
        addressService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}