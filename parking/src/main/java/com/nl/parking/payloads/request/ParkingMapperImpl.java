package com.nl.parking.payloads.request;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nl.parking.entity.Parking;

@Component
public class ParkingMapperImpl implements ParkingMapper {

    @Override
    public ParkingDTO parkingToParkingDTO(Parking parking) {
        if ( parking == null ) {
            return null;
        }

        ParkingDTO parkingDTO = new ParkingDTO();
        parkingDTO.setAmount(parking.getAmount());
        parkingDTO.setId(parking.getId());
        parkingDTO.setStreetName(parking.getStreetName());
        parkingDTO.setLicencePlateNumber(parking.getLicencePlateNumber());
        parkingDTO.setStartTime(parking.getStartTime());
        parkingDTO.setEndTime(parking.getEndTime());
        return parkingDTO;
    }

    @Override
    public List<ParkingDTO> parkingListToParkingDTOList(List<Parking> parkings) {
        if ( parkings == null ) {
            return null;
        }

        List<ParkingDTO> list = new ArrayList<ParkingDTO>( parkings.size() );
        for ( Parking parking : parkings ) {
            list.add( parkingToParkingDTO( parking ) );
        }

        return list;
    }
}
