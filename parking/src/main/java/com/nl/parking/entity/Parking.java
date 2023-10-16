package com.nl.parking.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "parking_registration")
@Data
public class Parking {

	@Id
	private long id;
	
	@Column(name = "street_name")
	private String streetName;
	
	@Column(name = "licence_plate_number")
	private String licencePlateNumber;
	
	@Column(name = "request_start_time")
	private LocalDateTime startTime;
	
	@Column(name = "request_end_time")
	private LocalDateTime endTime;
	
	@Column
	private BigDecimal amount;
}
