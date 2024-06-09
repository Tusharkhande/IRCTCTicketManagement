package com.tk.seat_service.repository;

import com.tk.seat_service.model.StationToSeatMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<StationToSeatMapping, Integer> {
    StationToSeatMapping findByStation(String stationName);


    List<StationToSeatMapping> findAllByTrainTrainId(String trainId);
}
