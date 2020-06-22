package com.iotnetwork.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iotnetwork.platform.exception.AuthenticationException;
import com.iotnetwork.platform.exception.BadDataException;
import com.iotnetwork.platform.exception.ServiceException;
import com.iotnetwork.platform.exception.ValidationException;
import com.iotnetwork.platform.service.PersistDataService;

@RestController
@EnableAutoConfiguration
@ComponentScan(value = "com.iotnetwork.platform")
@RequestMapping(value = "/sensordata") // Do I need this?
public class Controller {

	@Autowired
	private PersistDataService persistDataService;

	@RequestMapping(method = RequestMethod.POST, value = "/save", consumes = MediaType.TEXT_PLAIN_VALUE)
	public void saveReading(@RequestBody String reading)
			throws ServiceException, ValidationException, AuthenticationException, BadDataException {
		// Calling the service to persist the data
		persistDataService.saveReading(reading);
	}

//	@RequestMapping(method = RequestMethod.GET, value = "/data", consumes = MediaType.TEXT_PLAIN_VALUE)
//	public String fetchReading(@RequestHeader(value = AUTHORIZATION, defaultValue = NO_AUTH) String authHeader)
//			throws ServiceException, ValidationException, AuthenticationException, BadDataException {
//		// Calling the service to fetch the requested data after performing basic
//		// authentication of the user requesting the data
//		return persistDataService.fetchReading(authHeader);
//	}
}
