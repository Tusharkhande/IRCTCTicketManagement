package com.tk.booking_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    private String bookingId;
    private String trainId;
    private String source;
    private String destination;

    private int seatNumber;
    private String status;
}
