package com.denver.abs;

import com.denver.abs.routing.data.ConveyorNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AirportConveyorDataTest {

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
	AirportConveyorDataI airportConveyorDataI;
	List<ConveyorNode> listConveyorNode;
	
	@Before
	public void init()
	{
		airportConveyorDataI = new AirportConveyorData(conveyorInputData);
		listConveyorNode = airportConveyorDataI.getConveyorData();
		System.out.println(listConveyorNode);
	}
	
	//@Test
	public void testTicketingNodeEdges() {
		String nodeName = "Ticketing";
		validateEdgeNodes(nodeName, "A5", 0);
	}
	
	//@Test
	public void testBaggageClaimNodeEdges() {
		String nodeName = "Baggage_claim";
		validateEdgeNodes(nodeName, "A5", 0);
	}
	
	@Test
	public void testA5NodeEdges() {
		String nodeName = "A5";
		validateEdgeNodes(nodeName, "Ticketing", 0);
		validateEdgeNodes(nodeName, "Baggage_claim", 1);
		validateEdgeNodes(nodeName, "A10", 2);
		validateEdgeNodes(nodeName, "A1", 3);
	}
	
	@Test
	public void testA1NodeEdges() {
		String nodeName = "A1";
		validateEdgeNodes(nodeName, "A5", 0);
		validateEdgeNodes(nodeName, "A2", 1);
	}
	
	@Test
	public void testA2NodeEdges() {
		String nodeName = "A2";
		validateEdgeNodes(nodeName, "A1", 0);
		validateEdgeNodes(nodeName, "A3", 1);
	}
	
	@Test
	public void testA10NodeEdges() {
		String nodeName = "A10";
		validateEdgeNodes(nodeName, "A5", 0);
		validateEdgeNodes(nodeName, "A9", 1);
	}

	@Test
	public void testA9NodeEdges() {
		String nodeName = "A9";
		validateEdgeNodes(nodeName, "A10", 0);
		validateEdgeNodes(nodeName, "A8", 1);
	}
	
	private void validateEdgeNodes(String nodeName, String expectedNode, int index) {
		for (ConveyorNode conveyorNode : listConveyorNode) {
			if(conveyorNode.getName().equals(nodeName))
			{
				Assert.assertEquals(conveyorNode.getConveyorEdgesList().get(index).getTarget().getName(), expectedNode);
			}
		}
	}

}
