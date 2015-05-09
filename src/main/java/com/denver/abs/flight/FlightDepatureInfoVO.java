package com.denver.abs.flight;

public class FlightDepatureInfoVO {
	private String flightNumber;
	private String destinationConveyorNodeName;
	private String departingCity;
	
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getConveyorNodeName() {
		return destinationConveyorNodeName;
	}
	public void setConveyorNodeName(String conveyorNodeName) {
		this.destinationConveyorNodeName = conveyorNodeName;
	}
	public String getDepartingCity() {
		return departingCity;
	}
	public void setDepartingCity(String departingCity) {
		this.departingCity = departingCity;
	}

}
