package com.nl.parking.payloads.request;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UnRegisteredLicencePlateNumberRequest {
	private Long id;
	private String streetName;
	private String licencePlateNumber;
	private LocalDateTime dateOfObservation;

}