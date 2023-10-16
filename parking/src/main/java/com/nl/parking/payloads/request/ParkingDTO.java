package com.nl.parking.payloads.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ParkingDTO {
	
	private long id;
	private String streetName;	
	private String licencePlateNumber;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private BigDecimal amount;
}
