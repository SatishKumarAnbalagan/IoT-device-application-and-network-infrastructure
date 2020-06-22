package com.iotnetwork.platform.service;

import com.iotnetwork.platform.exception.AuthenticationException;
import com.iotnetwork.platform.exception.BadDataException;
import com.iotnetwork.platform.exception.ServiceException;
import com.iotnetwork.platform.exception.ValidationException;

public interface PersistDataService {

	public void saveReading(String reading)
			throws ServiceException, ValidationException, AuthenticationException, BadDataException;

//	public String fetchReading(String authHeader)
//			throws ServiceException, ValidationException, AuthenticationException, BadDataException;

	
}
