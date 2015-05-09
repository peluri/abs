package com.denver.abs;

import com.denver.abs.baggage.BaggagePathDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AirportBaggageServiceTest {
	private AirportBaggageServiceI pathFinder;
	
	private String[][] conveyorInputData = { 
			   { "Ticketing", "A5", "5" }, 
			   { "A5", "Baggage_claim", "5" }, 
			   { "A5", "A10", "4" },  
			   { "A5", "A1", "6" }, 
			   { "A1", "A2", "1" }, 
			   { "A2", "A3", "1" }, 
			   { "A3", "A4", "1" }, 
			   { "A10", "A9", "1" }, 
			   { "A9", "A8", "1" },  
			   { "A8", "A7", "1" }, 
			   { "A7", "A6", "1" },
			};
	
	private String[][] depatureInputData = { 
			   { "UA10", "A1", "MIA" }, 
			   { "UA11", "A1", "LAX" }, 
			   { "UA12", "A1", "JFK" },  
			   { "UA13", "A2", "JFK" }, 
			   { "UA14", "A2", "JFK" },  
			   { "UA15", "A2", "JFK" }, 
			   { "UA16", "A3", "LAX" }, 
			   { "UA17", "A4", "JFK" },  
			   { "UA18", "A5", "JFK" },
			   { "UA19", "A6", "JFK" },
			};
	
	
	@Before
	public void init()
	{
		pathFinder = new AirportBaggageService();
	}

	@Test
	public void testOptimalPathForProvidedTestSet() throws Exception {
		
		String[][] passengerBagsInputData = { 
				   { "0001", "Ticketing", "UA12" }, 
				   { "0002", "A5", "UA17" },  
				   { "0003", "A2", "UA10" }, 
				   { "0004", "A8", "UA18" },
				   { "0005", "A7", "Baggage_claim" },			   
				};
		
		String[][] expectedOutput = {
				   { "0001", "11.0", ""}, 
				   { "0002", "9.0" },  
				   { "0003", "1.0" }, 
				   { "0004", "6.0" },
				   { "0005", "12.0" },	
				};
	 	
		validateBaggagePathAndDistance(passengerBagsInputData, expectedOutput);
	}
	
	@Test
	public void testOptimalPathForBaggeClaim() throws Exception {
		String[][] passengerBagsInputData = { { "0099", "A5", "Baggage_claim" }, };
		String[][] expectedOutput = { { "0099", "5.0", ""}, };
	 	
		validateBaggagePathAndDistance(passengerBagsInputData, expectedOutput);
	}

	@Test
	public void testOptimalPathForTicketing() throws Exception {
		String[][] passengerBagsInputData = { { "1099", "Ticketing", "UA17" }, };
		String[][] expectedOutput = { { "1099", "14.0", ""}, };
	 	
		validateBaggagePathAndDistance(passengerBagsInputData, expectedOutput);
	}
	
	@Test
	public void testOptimalPathForConnectingFlights() throws Exception {
		String[][] passengerBagsInputData = { { "1199", "A4", "UA19" }, };
		String[][] expectedOutput = { { "1199", "17.0", "" }, };

		validateBaggagePathAndDistance(passengerBagsInputData, expectedOutput);
	}
	
	private void validateBaggagePathAndDistance(
			String[][] passengerBagsInputData, String[][] expectedOutput)
			throws Exception {
		List<BaggagePathDTO> listBaggagePathDTO = pathFinder.getOptimalBaggageTransferPath(conveyorInputData, depatureInputData, passengerBagsInputData);
		BaggagePathDTO baggagePathDTO;
		
		for (int i = 0; i < listBaggagePathDTO.size(); i++) {
			baggagePathDTO = listBaggagePathDTO.get(i);
			Assert.assertEquals(baggagePathDTO.getBaggageID(), expectedOutput[i][0]);
			Assert.assertEquals(baggagePathDTO.getOptimalDistanceFromOrigin()+"", expectedOutput[i][1]);
			
		}
	}

}
