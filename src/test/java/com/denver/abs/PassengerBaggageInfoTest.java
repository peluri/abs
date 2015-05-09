package com.denver.abs;

import com.denver.abs.baggage.PassengerBaggageData;
import com.denver.abs.baggage.PassengerBaggageI;
import com.denver.abs.baggage.PassengerBaggageInfoVO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PassengerBaggageInfoTest {

	private PassengerBaggageI passengerBaggageI;
	List<PassengerBaggageInfoVO> listPassengerBaggageInfoVO;
	
	@Before
	public void init()
	{
		String[][] passengerBagsInputData = { 
				   { "0001", "Ticketing", "UA12" }, 
				   { "0002", "A5", "UA17" },  
				   { "0003", "A2", "UA10" }, 
				   { "0004", "A8", "UA18" },
				   { "0005", "A7", "Baggage_claim" },			   
				};
		
		String[][] depatureInputData = { 
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
		
		passengerBaggageI = new PassengerBaggageData(passengerBagsInputData, depatureInputData);
		listPassengerBaggageInfoVO = passengerBaggageI.getPassengerData();
	}

	@Test
	public void testPassengerData() {
		Assert.assertEquals(listPassengerBaggageInfoVO.get(0).getBaggageID(), "0001");
		Assert.assertEquals(listPassengerBaggageInfoVO.get(1).getBaggageID(), "0002");
		Assert.assertEquals(listPassengerBaggageInfoVO.get(2).getBaggageID(), "0003");
		Assert.assertEquals(listPassengerBaggageInfoVO.get(3).getBaggageID(), "0004");
		Assert.assertEquals(listPassengerBaggageInfoVO.get(4).getBaggageID(), "0005");
	}
	
	@Test
	public void testDerivedDestinationConveyorName() {
		Assert.assertEquals(listPassengerBaggageInfoVO.get(0).getDerivedDestConveyorName(), "A1");
		Assert.assertEquals(listPassengerBaggageInfoVO.get(1).getDerivedDestConveyorName(), "A4");
		Assert.assertEquals(listPassengerBaggageInfoVO.get(2).getDerivedDestConveyorName(), "A1");
		Assert.assertEquals(listPassengerBaggageInfoVO.get(3).getDerivedDestConveyorName(), "A5");
	}

}
