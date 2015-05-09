package com.denver.abs.routing.data;
public class ConveyorEdge
{
	private ConveyorNode target;
	private double weight;

	public ConveyorEdge(ConveyorNode argTarget, double argWeight) {
		target = argTarget;
		weight = argWeight;
	}
	
	
	public ConveyorNode getTarget() {
		return target;
	}


	public double getWeight() {
		return weight;
	}


	@Override
	public String toString() {
		return "Edge [target.name=" + target.getName() + ", weight=" + weight + "]";
	}
}