package com.denver.abs.baggage;

import com.denver.abs.routing.data.ConveyorNode;

import java.util.List;

public class BaggagePathDTO {
	private String baggageID;
	private List<ConveyorNode> baggagePath;
	private Double optimalDistanceFromOrigin;
	
	public String getBaggageID() {
		return baggageID;
	}
	public void setBaggageID(String baggageID) {
		this.baggageID = baggageID;
	}
	public List<ConveyorNode> getBaggagePath() {
		return baggagePath;
	}
	public void setBaggagePath(List<ConveyorNode> baggagePath) {
		this.baggagePath = baggagePath;
	}
	public Double getOptimalDistanceFromOrigin() {
		return optimalDistanceFromOrigin;
	}
	public void setOptimalDistanceFromOrigin(Double optimalDistanceFromOrigin) {
		this.optimalDistanceFromOrigin = optimalDistanceFromOrigin;
	}

}
