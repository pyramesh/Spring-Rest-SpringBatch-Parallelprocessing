package com.ramesh.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ramesh.domain.Amenity;
/**
 * @author Ramesh.Yaleru
 *
 */
@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Long>{

}
