package com.ramesh.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ramesh.domain.HotelAmenity;

/**
 * @author Ramesh.Yaleru
 *
 */
@Repository
public interface HotelAmenityRepository extends JpaRepository<HotelAmenity, Long>{

	List<HotelAmenity> findByHotelId(Long hotelId);
}
