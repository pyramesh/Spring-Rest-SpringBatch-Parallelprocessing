package com.ramesh.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Ramesh.Yaleru
 *
 */

@Entity
@Table(name="rooms")
public class Room {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max = 50)
	@Column(name="description")
	private String description;
	

	@ManyToOne
    private Hotel hotel;
	
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(name="room_amenities",
			joinColumns= { @JoinColumn(name="room_id")},
			inverseJoinColumns= {@JoinColumn(name="amenity_id")})
	private Set<Amenity> amenities = new HashSet<>();


	
	public Room() {
		
	}
	
	
	public Room(@NotNull @Size(max = 50) String description) {
		super();
		this.description = description;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Hotel getHotel() {
		return hotel;
	}


	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}


	public Set<Amenity> getAmenities() {
		return amenities;
	}


	public void setAmenities(Set<Amenity> amenities) {
		this.amenities = amenities;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room )) return false;
        return id != null && id.equals(((Room) o).id);
    }
    @Override
    public int hashCode() {
        return 31;
    }
    
}
