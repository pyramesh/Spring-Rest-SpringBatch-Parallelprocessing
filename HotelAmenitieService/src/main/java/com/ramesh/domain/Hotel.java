package com.ramesh.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Ramesh.Yaleru
 *
 */

@Entity
@Table(name="hotels")
public class Hotel{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max = 50)
	@Column(name="hotel_name")
	private String hotelName;
	
	@NotNull
	@Size(max = 500)
	@Column(name="description")
	private String description;
	
	@NotNull
	@Size(max = 3)
	@Column(name="city_code")
	private String cityCode;
	
	
	@OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="hotel_id")
	private Set<Room> rooms;
	
	
	
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
   
	@JoinTable(name="hotel_amenities",
			joinColumns= { @JoinColumn(name="hotel_id")},
			inverseJoinColumns= {@JoinColumn(name="amenity_id")})
	private List<Amenity> amenities = new ArrayList<>();
	
	
	
	
	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private Set<HotelAmenity> hotelAmenities;
	
    
	public Hotel() {
		
	}
	
	public Hotel(String hotelName, String description,String cityCode) {
		super();
		this.hotelName = hotelName;
		this.description = description;
		this.cityCode = cityCode;
	}
	
	
	public Hotel(String hotelName, String description,String cityCode, Set<HotelAmenity> hotelAmenities,Set<Room> rooms) {
		super();
		this.hotelName = hotelName;
		this.description = description;
		this.cityCode = cityCode;
		for( HotelAmenity hAmenity : hotelAmenities ) {
			hAmenity.setHotel(this);
		}
		this.hotelAmenities =hotelAmenities;
		this.rooms= rooms;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public List<Amenity> getAmenities() {
		return amenities;
	}

	public void setAmenities(List<Amenity> amenities) {
		this.amenities = amenities;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	
	
	
	
	
	
}
