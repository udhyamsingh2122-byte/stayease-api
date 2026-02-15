package com.stayease.stayease.service;

import com.stayease.stayease.entity.Hotel;
import com.stayease.stayease.exception.CustomException;
import com.stayease.stayease.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Hotel updateHotel(Long id, Hotel updatedHotel) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() ->  new CustomException("Hotel not found"));


        hotel.setName(updatedHotel.getName());
        hotel.setLocation(updatedHotel.getLocation());
        hotel.setDescription(updatedHotel.getDescription());
        hotel.setAvailableRooms(updatedHotel.getAvailableRooms());

        return hotelRepository.save(hotel);
    }

    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }
}
