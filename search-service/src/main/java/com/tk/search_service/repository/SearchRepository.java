package com.tk.search_service.repository;

import com.tk.search_service.dto.Stations;
import com.tk.search_service.dto.Route;
import com.tk.search_service.model.Routes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<Routes, Integer>{
    @Query("SELECT new com.tk.search_service.dto.Route(R1.trainId, R1.station, R1.arrivalTime, R1.departureTime) " +
            "FROM Routes AS R1 " +
            "INNER JOIN Routes AS R2 ON R1.trainId = R2.trainId " +
            "WHERE R1.station = :source AND R2.station = :destination")
    List<Route> findTrains(@Param("source") String source, @Param("destination") String destination);

    @Query("SELECT new com.tk.search_service.dto.Stations(R.station) " +
            "FROM Routes AS R " +
            "WHERE R.trainId = :trainId")
    List<Stations> findByTrainId(String trainId);
}
