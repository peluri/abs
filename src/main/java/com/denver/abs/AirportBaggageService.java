package com.denver.abs;

import com.denver.abs.baggage.BaggagePathDTO;
import com.denver.abs.baggage.PassengerBaggageData;
import com.denver.abs.baggage.PassengerBaggageI;
import com.denver.abs.baggage.PassengerBaggageInfoVO;
import com.denver.abs.routing.ConveyorPathFinderI;
import com.denver.abs.routing.DijkstraPathFinder;

import java.util.ArrayList;
import java.util.List;

public class AirportBaggageService implements com.denver.abs.AirportBaggageServiceI
{
	// DI autowire the below
	AirportConveyorDataI airportConveyorDataI;
	PassengerBaggageI passengerBaggageI;
	ConveyorPathFinderI pathFinder;
	
	@Override
	public List<BaggagePathDTO> getOptimalBaggageTransferPath(String[][] conveyorDataFromAirportSystem,
			String[][] depatureDataFromAirController, String[][] passengerBagsFromAirlines) throws Exception 
	{
		List<BaggagePathDTO> listBaggagePathDTO = new ArrayList<BaggagePathDTO>();
		BaggagePathDTO baggagePathDTO;
		
		airportConveyorDataI = new AirportConveyorData(conveyorDataFromAirportSystem);
		passengerBaggageI = new PassengerBaggageData(passengerBagsFromAirlines, depatureDataFromAirController);
		
		List<PassengerBaggageInfoVO> listCustomerBaggageInfo = passengerBaggageI.getPassengerData();
		
		for (PassengerBaggageInfoVO baggageInfoVO : listCustomerBaggageInfo) {
			baggagePathDTO = new BaggagePathDTO();
			baggagePathDTO.setBaggageID(baggageInfoVO.getBaggageID());
			listBaggagePathDTO.add(baggagePathDTO);
			
			pathFinder = new DijkstraPathFinder(airportConveyorDataI.getConveyorData());
			pathFinder.findOptimalPath(baggageInfoVO, baggagePathDTO);
			System.out.println("baggagePathDTO: " + baggagePathDTO.getBaggageID() + ", getBaggagePath=" + baggagePathDTO.getBaggagePath() + ", Distance=" + baggagePathDTO.getOptimalDistanceFromOrigin());
		}
		return listBaggagePathDTO;
	}
}