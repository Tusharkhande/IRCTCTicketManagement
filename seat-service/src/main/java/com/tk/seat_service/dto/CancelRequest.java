package com.tk.seat_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelRequest {
    private String trainId;
    private String source;
    private String destination;
}
