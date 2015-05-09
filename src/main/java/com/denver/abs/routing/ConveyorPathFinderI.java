package com.denver.abs.routing;

import com.denver.abs.baggage.BaggagePathDTO;
import com.denver.abs.baggage.PassengerBaggageInfoVO;

public interface ConveyorPathFinderI {

	public abstract void findOptimalPath(PassengerBaggageInfoVO baggageInfoVO,
			BaggagePathDTO baggagePathDTO) throws Exception;

}