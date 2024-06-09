package com.tk.booking_service.repository;

import com.tk.booking_service.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String>{
    Booking findByBookingId(String bookingId);
}
