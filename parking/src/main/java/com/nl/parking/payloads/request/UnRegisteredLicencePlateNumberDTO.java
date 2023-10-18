package com.nl.parking.payloads.request;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UnRegisteredLicencePlateNumberDTO {
	
	private long id;
	private String streetName;	
	private String licencePlateNumber;
	private LocalDateTime dateOfObservation;
}
