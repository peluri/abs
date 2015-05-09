package com.denver.abs;

import com.denver.abs.routing.data.ConveyorNode;

import java.util.List;

public interface AirportConveyorDataI {
	public abstract List<ConveyorNode> getConveyorData();
}