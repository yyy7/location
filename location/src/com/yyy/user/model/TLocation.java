package com.yyy.user.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * TLocation entity. @author MyEclipse Persistence Tools
 */

public class TLocation implements java.io.Serializable {

	// Fields

	private Integer id;
	private String latitude;
	private String longitude;
	private String city;
	private Date ctime;

	// Constructors

	/** default constructor */
	public TLocation() {
	}

	/** minimal constructor */
	public TLocation(String latitude, String longitude, String city) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.city = city;
	}

	/** full constructor */
	public TLocation(String latitude, String longitude, String city, Date ctime) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.city = city;
		this.ctime = ctime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getCtime() {
		return this.ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

}