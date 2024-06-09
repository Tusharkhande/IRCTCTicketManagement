package com.tk.booking_service.controller;

import com.tk.booking_service.dto.BookingRequest;
import com.tk.booking_service.dto.CancelRequest;
import com.tk.booking_service.dto.SearchResponse;
import com.tk.booking_service.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public String bookTicket(@RequestBody SearchResponse searchResponse){

        return bookingService.bookTicket(searchResponse);
    }

    @PostMapping("/cancel/{bookingId}")
    public String cancelTicket(@PathVariable String bookingId){
        return bookingService.cancelTicket(bookingId);
    }
}
