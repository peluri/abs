package com.denver.abs;

import com.denver.abs.baggage.BaggagePathDTO;

import java.util.List;

public interface AirportBaggageServiceI {

	public abstract List<BaggagePathDTO> getOptimalBaggageTransferPath(
			String[][] conveyorDataFromAirportSystem,
			String[][] depatureDataFromAirController,
			String[][] passengerBagsFromAirlines) throws Exception;

}