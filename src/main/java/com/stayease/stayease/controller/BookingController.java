package com.stayease.stayease.controller;

import com.stayease.stayease.entity.Booking;
import com.stayease.stayease.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    // CUSTOMER only
    @PostMapping("/hotels/{hotelId}/book")
    @PreAuthorize("hasRole('CUSTOMER')")
    public Booking bookHotel(@PathVariable Long hotelId,
                             Authentication authentication) {

        String email = authentication.getName();
        return bookingService.bookHotel(hotelId, email);
    }

    // HOTEL_MANAGER only
    @DeleteMapping("/bookings/{bookingId}")
    @PreAuthorize("hasRole('HOTEL_MANAGER')")
    public void cancelBooking(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
    }
}
