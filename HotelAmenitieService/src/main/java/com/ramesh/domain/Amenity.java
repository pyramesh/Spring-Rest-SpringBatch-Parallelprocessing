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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Ramesh.Yaleru
 *
 */

@Entity
@Table(name="amenity_mst")
public class Amenity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="amenity_id")
	private Long id;
	
	@NotNull
	@Size(max = 10)
	@Column(name="short_desc")
	private String shortDescription;
	
	@NotNull
	@Size(max = 200)
	@Column(name="description")
	private String description;

	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            }, mappedBy="amenities")
    
	private Set<Hotel> hotels = new HashSet<>();
	
	
	@OneToMany(mappedBy = "amenity", cascade = CascadeType.ALL)
    private Set<HotelAmenity> hotelAmenities = new HashSet<>();
	
	public Amenity() {
		
	}
	
	public Amenity(String shortDescription, String description) {
		super();
		this.shortDescription = shortDescription;
		this.description = description;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Set<Hotel> getHotels() {
		return hotels;
	}


	public void setHotels(Set<Hotel> hotels) {
		this.hotels = hotels;
	}

	
}
