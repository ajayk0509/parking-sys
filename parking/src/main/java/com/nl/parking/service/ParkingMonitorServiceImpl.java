package com.nl.parking.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nl.parking.entity.UnRegisteredLicencePlateNumber;
import com.nl.parking.payloads.request.UnRegisteredLicencePlateNumberRequest;
import com.nl.parking.repository.UnRegisteredLicencePlateNumberRepository;

@Service
public class ParkingMonitorServiceImpl implements ParkingMonitorService{

	private static final Logger log = LoggerFactory.getLogger(ParkingMonitorServiceImpl.class);
	
	@Autowired
	private UnRegisteredLicencePlateNumberRepository unRegisteredLicencePlateNumberRepository; 
	
	@Override
	public List<UnRegisteredLicencePlateNumber> catureUnRegisteredLicPlates(List<UnRegisteredLicencePlateNumberRequest> unRegisteredLicencePlateNumberRequestList) {
		LocalDateTime dateOfObservation = LocalDateTime.now();
		List<UnRegisteredLicencePlateNumber> unRegisteredLicPlateNumbers = new ArrayList<UnRegisteredLicencePlateNumber>(unRegisteredLicencePlateNumberRequestList.size());
		for(UnRegisteredLicencePlateNumberRequest unRegisteredLicencePlateNumberRequest : unRegisteredLicencePlateNumberRequestList) {
		UnRegisteredLicencePlateNumber unRegisteredLicencePlateNumber = new UnRegisteredLicencePlateNumber();
		unRegisteredLicencePlateNumber.setStreetName(unRegisteredLicencePlateNumberRequest.getStreetName());
		unRegisteredLicencePlateNumber.setLicencePlateNumber(unRegisteredLicencePlateNumberRequest.getLicencePlateNumber());
		unRegisteredLicencePlateNumber.setDateOfObservation(dateOfObservation);
		unRegisteredLicPlateNumbers.add(unRegisteredLicencePlateNumber);
		}
		return unRegisteredLicencePlateNumberRepository.saveAll(unRegisteredLicPlateNumbers);
	}

	@Override
	public List<UnRegisteredLicencePlateNumber> getAllUnRegisteredLicencePlateNumber() {
		// TODO We can send the fine notification from here while fetching all the unregistered licence plates
		return unRegisteredLicencePlateNumberRepository.findAll();
	}
}
