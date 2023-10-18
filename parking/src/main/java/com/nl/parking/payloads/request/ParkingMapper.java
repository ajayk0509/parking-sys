package com.nl.parking.payloads.request;

import java.util.List;

import com.nl.parking.entity.Parking;
import com.nl.parking.entity.UnRegisteredLicencePlateNumber;

public interface ParkingMapper {

	ParkingDTO parkingToParkingDTO(Parking parking);
	List<ParkingDTO> parkingListToParkingDTOList(List<Parking> parkings);
	UnRegisteredLicencePlateNumberDTO licencePlateNumberToLicencePlateNumberDTO(UnRegisteredLicencePlateNumber unRegisteredLicencePlateNumber);
	List<UnRegisteredLicencePlateNumberDTO> licencePlateNumberToLicencePlateNumberDTOList(List<UnRegisteredLicencePlateNumber> unRegisteredLicencePlateNumbers);
}
