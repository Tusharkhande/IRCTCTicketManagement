package com.tk.seat_service.service;

import com.tk.seat_service.dto.BookingRequest;
import com.tk.seat_service.dto.CancelRequest;
import com.tk.seat_service.model.StationToSeatMapping;
import com.tk.seat_service.model.Train;
import com.tk.seat_service.repository.SeatRepository;
import com.tk.seat_service.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private TrainRepository trainRepository;

    public boolean isSeatAvailable(BookingRequest bookingRequest) {
        int flag = 0;
//        StationToSeatMapping stationToSeatMapping = seatRepository.findByStation(bookingRequest.getSource());
        List<StationToSeatMapping> stationToSeatMappings = seatRepository.findAllByTrainTrainId(bookingRequest.getTrainId());
//        if(stationToSeatMapping != null) {
//            int seats = stationToSeatMapping.getSeats();
//            Train train = trainRepository.findByTrainId(stationToSeatMapping.getTrain().getTrainId());
//            if(seats < train.getSeatCount()){
//                stationToSeatMapping.setSeats(seats+1);
//                seatRepository.save(stationToSeatMapping);
//                return true;
//            }
//        }

        for(StationToSeatMapping stationToSeatMapping : stationToSeatMappings){

            if(stationToSeatMapping.getStation().equals(bookingRequest.getSource())){
                flag = 1;
            }
            if(stationToSeatMapping.getStation().equals(bookingRequest.getDestination())){
                break;
            }

            if(flag == 1){
                int seats = stationToSeatMapping.getSeats();
                Train train = trainRepository.findByTrainId(stationToSeatMapping.getTrain().getTrainId());
                if(seats >= train.getSeatCount()){
                    return false;
                }else{
                    stationToSeatMapping.setSeats(seats + 1);
                    seatRepository.save(stationToSeatMapping);
                }
            }
        }
        return true;
    }

    public boolean cancelTicket(CancelRequest cancelRequest) {
//        StationToSeatMapping stationToSeatMapping = seatRepository.findByStation(station);
//        if(stationToSeatMapping != null) {
//            int seats = stationToSeatMapping.getSeats();
//            if(seats > 0){
//                stationToSeatMapping.setSeats(seats-1);
//                seatRepository.save(stationToSeatMapping);
//                return true;
//            }
//        }
        int flag = 0;
        List<StationToSeatMapping> stationToSeatMappings = seatRepository.findAllByTrainTrainId(cancelRequest.getTrainId());

        for(StationToSeatMapping stationToSeatMapping : stationToSeatMappings){
            if(stationToSeatMapping.getStation().equals(cancelRequest.getSource())){
                flag = 1;
            }
            if(stationToSeatMapping.getStation().equals(cancelRequest.getDestination())){
                return true;
            }

            if(flag == 1){
                int seats = stationToSeatMapping.getSeats();
                if(seats > 0){
                    stationToSeatMapping.setSeats(seats-1);
                    seatRepository.save(stationToSeatMapping);
                }
            }
        }
        return false;
    }
}
