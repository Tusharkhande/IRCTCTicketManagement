package com.tk.booking_service.service;

import com.tk.booking_service.dto.BookingRequest;
import com.tk.booking_service.dto.CancelRequest;
import com.tk.booking_service.dto.SearchResponse;
import com.tk.booking_service.model.Booking;
import com.tk.booking_service.repository.BookingRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Random;
import java.util.UUID;

@Service
@Builder
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public String bookTicket(SearchResponse searchResponse) {
        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setSource(searchResponse.getSource());
        bookingRequest.setDestination(searchResponse.getDestination());
        bookingRequest.setTrainId(searchResponse.getTrainId());

        try {
            Boolean result = webClientBuilder.build().post()
                    .uri("http://seat-service/seat/isAvailable")
                    .bodyValue(bookingRequest)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();

            if (result != null && result) {
                Booking booking = new Booking();
                booking.setBookingId(generateBookingId(bookingRequest));
                booking.setSource(bookingRequest.getSource());
                booking.setDestination(bookingRequest.getDestination());
                booking.setTrainId(bookingRequest.getTrainId());
                booking.setSeatNumber(generateRandomSeatNumber());
                booking.setStatus("CONFIRMED");
                bookingRepository.save(booking);
                return "Ticket booked successfully";
            } else {
                return "Seat not available, Please try again later.";
            }
        } catch (Exception e) {
            return "An error occurred while booking the ticket.";
        }
    }

    public String cancelTicket(String bookingId){
        Booking booking = bookingRepository.findByBookingId(bookingId);
        if(booking != null){
            if(booking.getStatus().equals("CANCELLED")){
                return "Ticket already cancelled";
            }
            booking.setStatus("CANCELLED");
            bookingRepository.save(booking);

            CancelRequest cancelRequest = new CancelRequest();
            cancelRequest.setTrainId(booking.getTrainId());
            cancelRequest.setSource(booking.getSource());
            cancelRequest.setDestination(booking.getDestination());

            Boolean isCancelled = webClientBuilder.build().post()
                    .uri("http://seat-service/seat/cancel")
                    .bodyValue(cancelRequest)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();

            if(isCancelled!=null && isCancelled){
                return "Ticket cancelled successfully";
            }else{
                return "Error occurred while cancelling the ticket. Please try again later.";
            }
        }else{
            return "Ticket not found";
        }
    }

    private String generateBookingId(BookingRequest bookingRequest) {
        return "B-" + bookingRequest.getSource().charAt(0) + bookingRequest.getDestination().charAt(0)+UUID.randomUUID().toString().substring(0, 4);
    }

    private int generateRandomSeatNumber() {
        Random random = new Random();
        return random.nextInt(5) + 1;
    }


}
