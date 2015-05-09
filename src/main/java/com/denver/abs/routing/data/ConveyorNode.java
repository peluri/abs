package com.denver.abs.routing.data;

import java.util.ArrayList;
import java.util.List;

public class ConveyorNode implements Comparable<ConveyorNode>
{
	private final String name;
	private List<ConveyorEdge> conveyorEdgesList;	
	private double distanceFromOrigin = Double.POSITIVE_INFINITY;
	private ConveyorNode previousConveyorNode;

	public List<ConveyorEdge> getConveyorEdgesList() {
		return conveyorEdgesList;
	}

	public void setConveyorEdgesList(List<ConveyorEdge> conveyorEdgesList) {
		this.conveyorEdgesList = conveyorEdgesList;
	}
	
	public void addConveyorEdge(ConveyorEdge conveyorEdge) {
		if(conveyorEdgesList == null)
			conveyorEdgesList = new ArrayList<ConveyorEdge>();

		conveyorEdgesList.add(conveyorEdge);
	}

	public String getName() {
		return name;
	}

	public ConveyorNode(String argName) {
		name = argName;
	}

	public double getDistanceFromOrigin() {
		return distanceFromOrigin;
	}

	public void setDistanceFromOrigin(double distanceFromOrigin) {
		this.distanceFromOrigin = distanceFromOrigin;
	}
	
	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(ConveyorNode other) {
		return Double.compare(distanceFromOrigin, other.distanceFromOrigin);
	}

	public ConveyorNode getPreviousConveyorNode() {
		return previousConveyorNode;
	}

	public void setPreviousConveyorNode(ConveyorNode previousConveyorNode) {
		this.previousConveyorNode = previousConveyorNode;
	}
}