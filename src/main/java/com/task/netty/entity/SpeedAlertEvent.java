package com.task.netty.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "speed_alert_events")
public class SpeedAlertEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "vehicle_speed")
    private double vehicleSpeed;

    private double latitude;

    private double longitude;

    private Timestamp timestamp;

    // Constructors, getters, and setters

    public SpeedAlertEvent() {
    }

    public SpeedAlertEvent(String deviceId, double vehicleSpeed, double latitude, double longitude, Timestamp timestamp) {
        this.deviceId = deviceId;
        this.vehicleSpeed = vehicleSpeed;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
    }
    

    // getters and setters
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

	public double getVehicleSpeed() {
		return vehicleSpeed;
	}

	public void setVehicleSpeed(double vehicleSpeed) {
		this.vehicleSpeed = vehicleSpeed;
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

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

}