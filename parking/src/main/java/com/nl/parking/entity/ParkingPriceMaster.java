package com.nl.parking.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "parking_price")
@Data
public class ParkingPriceMaster {

	@Id
	private Long id;
	
	@Column(name = "street_name")
	String streetName;
	
	@Column
	BigDecimal price;	
}
