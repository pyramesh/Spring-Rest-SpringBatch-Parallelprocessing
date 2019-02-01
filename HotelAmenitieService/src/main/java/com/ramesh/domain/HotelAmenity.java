package com.ramesh.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Ramesh.Yaleru
 *
 */
@Entity
@Table(name="hotel_amenities")
public class HotelAmenity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="hotel_id")
    private Hotel hotel;
 
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="amenity_id")
    private Amenity amenity;
    
    @Column(name="chargeable")
    private Boolean chargeable;
    
    @Column(name="amount")
    private double amount;
    
    public HotelAmenity() {
    	
    }
    
    public HotelAmenity(Amenity amenity, Boolean chargeable, double amount) {
    	this.amenity= amenity;
    	this.chargeable =chargeable;
    	this.amount= amount;
    }

    
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Amenity getAmenity() {
		return amenity;
	}

	public void setAmenity(Amenity amenity) {
		this.amenity = amenity;
	}

	public Boolean getChargeable() {
		return chargeable;
	}

	public void setChargeable(Boolean chargeable) {
		this.chargeable = chargeable;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        HotelAmenity Obj = (HotelAmenity) o;
        return Objects.equals(hotel, Obj.hotel) &&
               Objects.equals(amenity, Obj.amenity);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(hotel, amenity);
    }
    
}
