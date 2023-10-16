package com.nl.parking.payloads.request;

import java.util.List;

import com.nl.parking.entity.Parking;

public interface ParkingMapper {

	ParkingDTO parkingToParkingDTO(Parking parking);
	List<ParkingDTO> parkingListToParkingDTOList(List<Parking> parkings);
}
