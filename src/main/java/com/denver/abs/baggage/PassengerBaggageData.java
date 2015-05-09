package com.denver.abs.baggage;

import com.denver.abs.flight.*;
import com.denver.abs.baggage.*;
import java.util.ArrayList;
import java.util.List;


public class PassengerBaggageData implements PassengerBaggageI {
	private String[][] passengerBagsFromAirlines;
	private String[][] depatureDataFromAirController;
	
	public PassengerBaggageData(String[][] passengerBagsFromAirlines, String[][] depatureDataFromAirController)
	{
		this.passengerBagsFromAirlines = passengerBagsFromAirlines;
		this.depatureDataFromAirController = depatureDataFromAirController;
	}
	
	@Override
	public List<PassengerBaggageInfoVO> getPassengerData()
	{
		List<PassengerBaggageInfoVO> listBaggageInfo = new ArrayList<PassengerBaggageInfoVO>();
		
		PassengerBaggageInfoVO passengerBaggageInfo;
		for (int index = 0; index < passengerBagsFromAirlines.length; index++) {
			passengerBaggageInfo = new PassengerBaggageInfoVO();
			passengerBaggageInfo.setBaggageID(passengerBagsFromAirlines[index][0]);
			passengerBaggageInfo.setOriginConveyorName(passengerBagsFromAirlines[index][1]);
			passengerBaggageInfo.setFlightNumber(passengerBagsFromAirlines[index][2]);
			
			getDestConveyorNodeByFlightNumber(depatureDataFromAirController, passengerBaggageInfo);
			listBaggageInfo.add(passengerBaggageInfo);
		}
		return listBaggageInfo;
	}
	
	private  void getDestConveyorNodeByFlightNumber(String[][] depatureInputData, PassengerBaggageInfoVO passengerBaggageInfo) {
		if(isBaggageDestinationNotToFlight(passengerBaggageInfo)) return;

		for (int index = 0; index < depatureInputData.length; index++) {
			FlightDepatureInfoVO flightInfo = new FlightDepatureInfoVO();
			flightInfo.setConveyorNodeName(depatureInputData[index][1]);
			flightInfo.setDepartingCity(depatureInputData[index][2]);
			flightInfo.setFlightNumber(depatureInputData[index][0]);
			
			if(passengerBaggageInfo.getFlightNumber().equals(flightInfo.getFlightNumber()))
			{
				passengerBaggageInfo.setDerivedDestConveyorName(flightInfo.getConveyorNodeName());
				return;
			}
		}
	}
	
	private boolean isBaggageDestinationNotToFlight(PassengerBaggageInfoVO passengerBaggageInfo) {
		String flightNumber = passengerBaggageInfo.getFlightNumber();
		if("Baggage_claim".equalsIgnoreCase(flightNumber) || "Ticketing".equalsIgnoreCase(flightNumber))
		{
			passengerBaggageInfo.setDerivedDestConveyorName(flightNumber);
			return true;
		}
		return false;
	}
}
