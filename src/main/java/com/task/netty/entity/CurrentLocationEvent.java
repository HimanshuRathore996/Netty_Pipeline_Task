package com.task.netty.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "current_location_events")
public class CurrentLocationEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "device_id")
    private String deviceId;

    private double latitude;

    private double longitude;

    @Column(name = "vehicle_speed")
    private double vehicleSpeed;

    private Timestamp timestamp;
    
//    @Column(name = "event_type")
//    private String eventType;

    public CurrentLocationEvent() {
    }

    public CurrentLocationEvent(String deviceId, double latitude, double longitude, double vehicleSpeed, Timestamp timestamp, String eventType) {
        this.deviceId = deviceId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.vehicleSpeed = vehicleSpeed;
        this.timestamp = timestamp;
//        this.eventType = eventType;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getVehicleSpeed() {
		return vehicleSpeed;
	}

	public void setVehicleSpeed(double vehicleSpeed) {
		this.vehicleSpeed = vehicleSpeed;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

//	public String getEventType() {
//		return eventType;
//	}
//
//	public void setEventType(String eventType) {
//		this.eventType = eventType;
//	}
    
    
}