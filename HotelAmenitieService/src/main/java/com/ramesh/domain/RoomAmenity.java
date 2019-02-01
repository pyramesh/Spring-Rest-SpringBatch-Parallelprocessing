package com.ramesh.domain;

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
@Table(name="room_amenities")
public class RoomAmenity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="room_id")
    private Room room;
 
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="amenity_id")
    private Amenity amenity;
    
    @Column(name="chargeable")
    private Boolean chargeable;
    
    @Column(name="amount")
    private double amount;


    
    
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
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

    
    
}
