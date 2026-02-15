package com.stayease.stayease.service;

import com.stayease.stayease.entity.*;
import com.stayease.stayease.exception.CustomException;
import com.stayease.stayease.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;

    public Booking bookHotel(Long hotelId, String userEmail) {

        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() ->  new CustomException("Hotel not found")
);

        if (hotel.getAvailableRooms() <= 0) {
            throw new RuntimeException("No rooms available");
        }

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        hotel.setAvailableRooms(hotel.getAvailableRooms() - 1);

        Booking booking = Booking.builder()
                .hotel(hotel)
                .customer(user)
                .build();

        hotelRepository.save(hotel);

        return bookingRepository.save(booking);
    }

    public void cancelBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}
