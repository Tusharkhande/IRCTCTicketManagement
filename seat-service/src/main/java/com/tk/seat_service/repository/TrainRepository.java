package com.tk.seat_service.repository;

import com.tk.seat_service.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends JpaRepository<Train, String> {
    Train findByTrainId(String trainId);

}
