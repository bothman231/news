package com.botham.base;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonIgnoreProperties(ignoreUnknown = true)
public class XLocation implements Serializable {

	private static final long serialVersionUID = -3164505100189768971L;

	public XLocation() {
		this.ip="";
		this.city="";
		this.country="";
		this.loc="";
		this.org="";
		this.postal="";
		this.region="";
	}
	
	public XLocation(String ip, String city) {
		this.ip=ip;
		this.city=city;
		
		this.country="";
		this.loc="";
		this.org="";
		this.postal="";
		this.region="";
	}
	
	private String ip;
	private String city;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	private String region;
	private String country;
	private String loc;
	private String org;
	private String postal;

	public String getRegion() {
		return region;
	}

	@Override
	public String toString() {
		return "Location [ip=" + ip + ", city=" + city + ", region=" + region + ", country=" + country + ", loc=" + loc
				+ ", org=" + org + ", postal=" + postal + "]";
	}

	public void setRegion(String region) {
		this.region = region;
	}

}
