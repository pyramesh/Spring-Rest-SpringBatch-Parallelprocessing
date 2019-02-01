package com.ramesh.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ramesh.domain.RoomAmenity;

/**
 * @author Ramesh.Yaleru
 *
 */
@Repository
public interface RoomAmenityRepository extends JpaRepository<RoomAmenity, Long>{
	
	List<RoomAmenity> findByRoomId(Long roomId);
}
