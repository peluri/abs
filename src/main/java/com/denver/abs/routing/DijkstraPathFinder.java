package com.denver.abs.routing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import com.denver.abs.baggage.BaggagePathDTO;
import com.denver.abs.baggage.PassengerBaggageInfoVO;
import com.denver.abs.routing.data.ConveyorEdge;
import com.denver.abs.routing.data.ConveyorNode;

public class DijkstraPathFinder extends BaseConveyorPathFinder {
	private List<ConveyorNode> allConveyorNodes;
	public DijkstraPathFinder(List<ConveyorNode> allConveyorNodes)
	{
		this.allConveyorNodes = allConveyorNodes;
	}
	
	private void computePathsFromOriginConveyor(ConveyorNode origin)
    {
        origin.setDistanceFromOrigin(0.);
        PriorityQueue<ConveyorNode> finalPathQueue = new PriorityQueue<ConveyorNode>();
      	finalPathQueue.add(origin);
      	
      	// visit each node connected to origin Conveyor
		while (true) {
			ConveyorNode visitingConveyorNode = finalPathQueue.poll();

			// exit after all origin connected nodes are visited.
			if (visitingConveyorNode == null)
				break;

            // Visit each edge and compute the distance from Origin conveyor node
            for (ConveyorEdge edge : visitingConveyorNode.getConveyorEdgesList())
            {
                ConveyorNode edgeNode = edge.getTarget();
                double weight = edge.getWeight();
                double currentDistanceFromOriginNode = visitingConveyorNode.getDistanceFromOrigin() + weight;
               
				if (currentDistanceFromOriginNode < edgeNode.getDistanceFromOrigin()) {
					finalPathQueue.remove(edgeNode);
					edgeNode.setDistanceFromOrigin(currentDistanceFromOriginNode);
					edgeNode.setPreviousConveyorNode(visitingConveyorNode);
					finalPathQueue.add(edgeNode);
				}
            }
	    }
    }

	private List<ConveyorNode> getShortestPathToDestination(ConveyorNode target, String sourceNodeName) {
		List<ConveyorNode> path = new ArrayList<ConveyorNode>();
		for (ConveyorNode node = target; node != null; node = node.getPreviousConveyorNode()){
			path.add(node);
			if(sourceNodeName.equalsIgnoreCase(node.getName()))
				break;
		}

		Collections.reverse(path);
		return path;
	}

	@Override
	public void findOptimalPath(PassengerBaggageInfoVO baggageInfoVO,
			BaggagePathDTO baggagePathDTO) throws Exception
    {
		String originNodeName= baggageInfoVO.getOriginConveyorName();
		ConveyorNode originNode = getNodeByName(originNodeName, allConveyorNodes);
		ConveyorNode destNode = getNodeByName(baggageInfoVO.getDerivedDestConveyorName(), allConveyorNodes);
	    
    	computePathsFromOriginConveyor(originNode);
    	List<ConveyorNode> path = getShortestPathToDestination(destNode, originNodeName);
    	baggagePathDTO.setBaggagePath(path);
    	baggagePathDTO.setOptimalDistanceFromOrigin(destNode.getDistanceFromOrigin());	
	}
	
	
}
