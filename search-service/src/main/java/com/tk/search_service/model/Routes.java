package com.tk.search_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Routes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int routeId;

    private String trainId;
    private String station;
    private String arrivalTime;
    private String departureTime;
}
