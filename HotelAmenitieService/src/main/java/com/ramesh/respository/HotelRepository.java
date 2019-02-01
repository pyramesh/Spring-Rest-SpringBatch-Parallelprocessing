package com.ramesh.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ramesh.domain.Hotel;

/**
 * @author Ramesh.Yaleru
 *
 */
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>{

	
}
