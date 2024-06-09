package com.tk.seat_service.controller;

import com.tk.seat_service.dto.BookingRequest;
import com.tk.seat_service.dto.CancelRequest;
import com.tk.seat_service.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seat")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping("/isAvailable")
    public boolean isSeatAvailable(@RequestBody BookingRequest bookingRequest) {
        return seatService.isSeatAvailable(bookingRequest);
    }

    @PostMapping("/cancel")
    public boolean cancelTicket(@RequestBody CancelRequest cancelRequest){
        return seatService.cancelTicket(cancelRequest);
    }
}
