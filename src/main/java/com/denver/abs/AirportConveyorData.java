package com.denver.abs;

import com.denver.abs.routing.data.ConveyorEdge;
import com.denver.abs.routing.data.ConveyorNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is responsible for pulling the AirportConveyor data from Airport system
 *
 */
public class AirportConveyorData implements AirportConveyorDataI {
	private final String[][] conveyorInputData;
	
	public AirportConveyorData(String[][] conveyorInputData)
	{
		this.conveyorInputData = conveyorInputData;
	}
	

	@Override
	public List<ConveyorNode> getConveyorData() 
	{
		String nodeName, edgeNodeName, edgeNodeWeight;
		ConveyorNode conveyorNode, edgeNode;
		List<ConveyorNode> allConveyorNodes = new ArrayList<ConveyorNode>();
		Map<String, ConveyorNode> conveyorNodeMap = new HashMap<String, ConveyorNode>();
		
		// building Conveyor Nodes and Edges weighted bi-directional binary graph.
		for (int index = 0; index < conveyorInputData.length; index++) {
			nodeName = conveyorInputData[index][0];
			edgeNodeName = conveyorInputData[index][1];
			edgeNodeWeight = conveyorInputData[index][2];
					
			conveyorNode = prepareNodeMap(nodeName, allConveyorNodes, conveyorNodeMap);
			edgeNode = prepareNodeMap(edgeNodeName, allConveyorNodes, conveyorNodeMap);
	
			conveyorNode.addConveyorEdge(new ConveyorEdge(edgeNode, Integer.valueOf(edgeNodeWeight)));
			edgeNode.addConveyorEdge(new ConveyorEdge(conveyorNode, Integer.valueOf(edgeNodeWeight)));
		}
		return allConveyorNodes;
	}
	
	private  ConveyorNode prepareNodeMap(String nodeName,
			List<ConveyorNode> vertices, Map<String, ConveyorNode> conveyorNodeMap) 
	{
		ConveyorNode conveyorNode;
		if(conveyorNodeMap.get(nodeName) != null)
			conveyorNode = conveyorNodeMap.get(nodeName);
		else
		{
			conveyorNode = new ConveyorNode(nodeName);
			conveyorNodeMap.put(nodeName, conveyorNode);
			vertices.add(conveyorNode);
		}
		return conveyorNode;
	}
}
