package com.nl.parking.service;

import java.util.List;

import com.nl.parking.entity.UnRegisteredLicencePlateNumber;
import com.nl.parking.payloads.request.UnRegisteredLicencePlateNumberRequest;

public interface ParkingMonitorService {

	List<UnRegisteredLicencePlateNumber> catureUnRegisteredLicPlates(List<UnRegisteredLicencePlateNumberRequest> unRegisteredLicencePlateNumberRequest);	
	List<UnRegisteredLicencePlateNumber> getAllUnRegisteredLicencePlateNumber();
}
