package com.nl.parking.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nl.parking.entity.Parking;
import com.nl.parking.payloads.request.ParkingDTO;
import com.nl.parking.payloads.request.ParkingMapper;
import com.nl.parking.payloads.request.ParkingRegistrationRequest;
import com.nl.parking.service.ParkingRegistrationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@ApiResponse
@RestController
@RequestMapping("/v1/parking")
public class ParkingSystemController {

	private static final Logger log = LoggerFactory.getLogger(ParkingSystemController.class);
	
	@Autowired
	private ParkingRegistrationService parkingRegistrationService;
	
	@Autowired
	ParkingMapper parkingMapper; 
	
	@Operation(summary = "Parking registration")
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "car parking registration has been successfully"),
							@ApiResponse(responseCode = "500", description = "Internal Server Error")})
	@PostMapping("register")
	public ResponseEntity<ParkingDTO> registerParking(@RequestBody ParkingRegistrationRequest parkingRegistrationRequest){
		ResponseEntity<ParkingDTO> response = null;
		log.info("Request recieved for parking registration : {}", parkingRegistrationRequest);
		Parking parking = parkingRegistrationService.register(parkingRegistrationRequest);
		ParkingDTO parkingDTO = parkingMapper.parkingToParkingDTO(parking);
		//TODO Need to send notification to user
		//mailService.sendErrorEmails("Parking registration successfully",parking.toString());  // Need to configure the SMTP server details
		response = ResponseEntity.status(HttpStatus.CREATED).body(parkingDTO);
		return response;
	}
	
	@Operation(summary = "Parking unregistration")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@ApiResponses(value = { @ApiResponse(responseCode = "202", description = "car parking registration has been successfully"),
							@ApiResponse(responseCode = "500", description = "Internal Server Error")})
	@PutMapping("unregister")
	public ResponseEntity<ParkingDTO> unregisterParking(@RequestBody ParkingRegistrationRequest parkingRequest){
		ResponseEntity<ParkingDTO> response = null;
		Parking parking = parkingRegistrationService.unregister(parkingRequest);
		ParkingDTO parkingDTO = parkingMapper.parkingToParkingDTO(parking);
		response = ResponseEntity.status(HttpStatus.ACCEPTED).body(parkingDTO);
		return response;
	}
	
	@Operation(description = "List all parking registered")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "car parking registration has been successfully"),
							@ApiResponse(responseCode = "500", description = "Internal Server Error")})
	@GetMapping("all")
	public ResponseEntity<List<ParkingDTO>> getAllParkings(){
		ResponseEntity<List<ParkingDTO>> response = null;
		List<Parking> parkingList = parkingRegistrationService.getAllParkings();
		List<ParkingDTO> parkingDTOList = parkingMapper.parkingListToParkingDTOList(parkingList);
		response = ResponseEntity.status(HttpStatus.OK).body(parkingDTOList);
		return response;
	}
}
