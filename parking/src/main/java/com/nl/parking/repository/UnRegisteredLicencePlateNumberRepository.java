package com.nl.parking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nl.parking.entity.UnRegisteredLicencePlateNumber;

@Repository
public interface UnRegisteredLicencePlateNumberRepository extends JpaRepository<UnRegisteredLicencePlateNumber, String>{

	Optional<UnRegisteredLicencePlateNumber> findByLicencePlateNumber(String licencePlateNumber);
}
