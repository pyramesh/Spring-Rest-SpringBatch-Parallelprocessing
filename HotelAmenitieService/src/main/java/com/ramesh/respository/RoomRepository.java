package com.ramesh.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ramesh.domain.Room;
/**
 * @author Ramesh.Yaleru
 *
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{

	List<Room> findByHotelId(Long hotelId);
    Optional<Room> findByIdAndHotelId(Long id, Long hotelId);
}
