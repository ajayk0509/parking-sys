package com.nl.parking.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nl.parking.entity.Parking;
import com.nl.parking.entity.ParkingPriceMaster;
import com.nl.parking.exception.BadRequestException;
import com.nl.parking.payloads.request.ParkingRegistrationRequest;
import com.nl.parking.repository.ParkingPriceMasterRepository;
import com.nl.parking.repository.ParkingRepository;

@Service
public class ParkingRegistrationServiceImpl implements ParkingRegistrationService{

	private static final Logger log = LoggerFactory.getLogger(ParkingRegistrationServiceImpl.class);
	
	@Autowired
	private ParkingRepository parkingRepository;
	
	@Autowired
	private ParkingPriceMasterRepository parkingPriceMasterRepository; 
	
	@Override
	public Parking register(ParkingRegistrationRequest parkingRegistrationRequest) {
		LocalDateTime startTime = LocalDateTime.now(ZoneId.of("Europe/Amsterda"));		
		Parking parking = new Parking();
		parking.setStreetName(parkingRegistrationRequest.getStreetName());
		parking.setLicencePlateNumber(parkingRegistrationRequest.getLicencePlateNumber());
		parking.setStartTime(startTime);
		return parkingRepository.save(parking);
	}

	@Override
	public Parking unregister(ParkingRegistrationRequest parkingRegistrationRequest) {
		Optional<Parking> parkingOptional = parkingRepository.findByLicencePlateNumber(parkingRegistrationRequest.getLicencePlateNumber());
		return parkingOptional.filter(parking -> parking.getLicencePlateNumber().equals(parkingRegistrationRequest.getLicencePlateNumber())) 
		.map(parking -> {
			LocalDateTime endTime = LocalDateTime.now(ZoneId.of("Europe/Amsterda"));
			parking.setEndTime(endTime);
			if(isTimeBteween8amTo9Pm(parking.getStartTime())) {
				parking.setAmount(calculateParkingCharges(parking.getStreetName(), parking.getLicencePlateNumber(), parking.getStartTime(), endTime));
			}else {
				parking.setAmount(BigDecimal.valueOf(0));
			}			
			return parkingRepository.save(parking);
		}).orElseThrow(() -> {
			return new BadRequestException("Licence plate number is not registerd");
		});		
	}
	
	private BigDecimal calculateParkingCharges(String streetName, String licencePlateNumber, LocalDateTime startTime, LocalDateTime endTime) {
		Optional<ParkingPriceMaster> parkingMaster = parkingPriceMasterRepository.findByStreetName(streetName);
		if(parkingMaster.isPresent()) {
			BigDecimal price = parkingMaster.get().getPrice();
			long minutesBetween = Duration.between(startTime, endTime).toMinutes();		
			return price.multiply(BigDecimal.valueOf(minutesBetween));
		}
		return BigDecimal.valueOf(0);		
	}
	
	private boolean isTimeBteween8amTo9Pm(LocalDateTime startTime) {
			LocalTime localTime = LocalTime.of(startTime.getHour(), startTime.getMinute(), startTime.getSecond());
			LocalTime isBeforeTime = LocalTime.of(8, 0, 0);
			LocalTime isAfterTime = LocalTime.of(21, 0, 0);		    
		    if (localTime.isBefore(isBeforeTime) || localTime.isAfter(isAfterTime)) {//checkes whether the current time is between 08:00:00 and 21:00:00.		        
		    	return Boolean.FALSE;
		    }else {		
		    	return Boolean.TRUE;
		    }		
	}

	@Override
	public List<Parking> getAllParkings() {		
		return parkingRepository.findAll();
	}
}
