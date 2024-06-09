package com.tk.booking_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponse {
    private String trainId;
    private String source;
    private String destination;
    private List<String> path;
    private String arrivalTime;
    private String departureTime;
}
