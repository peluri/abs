package com.denver.abs.baggage;

public class PassengerBaggageInfoVO {
	private String baggageID;
	private String flightNumber;
	private String originConveyorName;
	private String derivedDestConveyorName;
	
	
	public String getBaggageID() {
		return baggageID;
	}
	public void setBaggageID(String baggageID) {
		this.baggageID = baggageID;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getOriginConveyorName() {
		return originConveyorName;
	}
	public void setOriginConveyorName(String originConveyorNodeName) {
		this.originConveyorName = originConveyorNodeName;
	}
	public String getDerivedDestConveyorName() {
		return derivedDestConveyorName;
	}
	public void setDerivedDestConveyorName(
			String derivedDestinationConveyorNode) {
		this.derivedDestConveyorName = derivedDestinationConveyorNode;
	}
	

}
