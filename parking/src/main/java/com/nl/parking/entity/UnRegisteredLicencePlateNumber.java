package com.nl.parking.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "unregistered_licence_plate_number")
@Data
public class UnRegisteredLicencePlateNumber {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "street_name")
	private String streetName;
	
	@Column(name = "licence_plate_number")
	private String licencePlateNumber;
	
	@Column(name = "date_of_observation")
	private LocalDateTime dateOfObservation;
}
