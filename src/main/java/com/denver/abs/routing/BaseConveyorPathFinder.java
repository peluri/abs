package com.denver.abs.routing;

import com.denver.abs.routing.data.ConveyorNode;

import java.util.List;

public abstract class BaseConveyorPathFinder implements ConveyorPathFinderI {

	protected ConveyorNode getNodeByName(String nodeName, List<ConveyorNode> allConveyorNodes) throws Exception
	{
		try{ 
			for (ConveyorNode node : allConveyorNodes)
			{   
				if(nodeName.equals(node.getName()))
					return node;
			}
		}
		catch(Exception e)
		{
			// notify HelpDesk with Sev 1.
		}
		throw new Exception("Couldn't able to find the Conveyor node for " + nodeName);
	}

}
