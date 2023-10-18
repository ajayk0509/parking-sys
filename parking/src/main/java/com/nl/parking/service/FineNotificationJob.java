package com.nl.parking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.nl.parking.payloads.request.ParkingMapper;
import com.nl.parking.payloads.request.UnRegisteredLicencePlateNumberDTO;

@Component
public class FineNotificationJob {
	
	@Autowired
	private ParkingMonitorService monitorService;
	
	@Autowired
	private ParkingMapper mapper;

	@Scheduled()
	public void getAllUnRegisteredLicPlates() {
		List<UnRegisteredLicencePlateNumberDTO> licencePlateNumberDTOs = mapper.licencePlateNumberToLicencePlateNumberDTOList(monitorService.getAllUnRegisteredLicencePlateNumber());
		licencePlateNumberDTOs.stream().forEach(UnRegisteredLicencePlateNumberDTO -> {
			// TODO Need to send the fine notification to corresponding user
		});
	}
}
