package com.nl.parking.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nl.parking.entity.UnRegisteredLicencePlateNumber;
import com.nl.parking.payloads.request.ParkingMapper;
import com.nl.parking.payloads.request.UnRegisteredLicencePlateNumberDTO;
import com.nl.parking.payloads.request.UnRegisteredLicencePlateNumberRequest;
import com.nl.parking.service.ParkingMonitorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@ApiResponse
@RestController
@RequestMapping("/v1/monitor/admin")
public class ParkingMonitorController {

	private static final Logger log = LoggerFactory.getLogger(ParkingMonitorController.class);
	
	@Autowired
	private ParkingMonitorService parkingMonitorService;
	
	@Autowired
	ParkingMapper parkingMapper;	
	
	@Operation(summary = "All non registered parking cature")
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "car parking registration has been successfully"),
							@ApiResponse(responseCode = "500", description = "Internal Server Error")})
	@PostMapping("capture/nonregistered")
	public ResponseEntity<List<UnRegisteredLicencePlateNumberDTO>> captureUnRegisterLicencePlateNumbers(@RequestBody List<UnRegisteredLicencePlateNumberRequest> unRegisteredLicencePlateNumberRequest){
		ResponseEntity<List<UnRegisteredLicencePlateNumberDTO>> response = null;
		log.info("Request recieved for registration licence number plate: {}", unRegisteredLicencePlateNumberRequest);
		List<UnRegisteredLicencePlateNumber> unRegisteredLicencePlateNumbers = parkingMonitorService.catureUnRegisteredLicPlates(unRegisteredLicencePlateNumberRequest);
		List<UnRegisteredLicencePlateNumberDTO> unRegisteredLicencePlateNumberDTOs = parkingMapper.licencePlateNumberToLicencePlateNumberDTOList(unRegisteredLicencePlateNumbers);		
		response = ResponseEntity.status(HttpStatus.CREATED).body(unRegisteredLicencePlateNumberDTOs);
		return response;
	}
	
	@Operation(description = "List all non registered licence plate number")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "all registered licence plate numbers fetched successfully"),
							@ApiResponse(responseCode = "500", description = "Internal Server Error")})
	@GetMapping("all/nonregistered")
	public ResponseEntity<List<UnRegisteredLicencePlateNumberDTO>> getAllUnRegisteredParkings(){
		ResponseEntity<List<UnRegisteredLicencePlateNumberDTO>> response = null;
		List<UnRegisteredLicencePlateNumber> unRegisteredLicencePlateNumbers = parkingMonitorService.getAllUnRegisteredLicencePlateNumber();
		List<UnRegisteredLicencePlateNumberDTO> parkingDTOList = parkingMapper.licencePlateNumberToLicencePlateNumberDTOList(unRegisteredLicencePlateNumbers);
		response = ResponseEntity.status(HttpStatus.OK).body(parkingDTOList);
		return response;
	}
}
