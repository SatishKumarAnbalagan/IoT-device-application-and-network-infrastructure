package com.iotnetwork.platform.service.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iotnetwork.platform.auth.LoginService;
import com.iotnetwork.platform.exception.AuthenticationException;
import com.iotnetwork.platform.exception.BadDataException;
import com.iotnetwork.platform.exception.ServiceException;
import com.iotnetwork.platform.exception.ValidationException;
import com.iotnetwork.platform.model.meta.UserMetaData;
import com.iotnetwork.platform.repo.CRUDRepository;
import com.iotnetwork.platform.service.AuthenticationService;
import com.iotnetwork.platform.service.FetchMetaDataService;
import com.iotnetwork.platform.service.PersistDataService;
import com.iotnetwork.platform.service.PreProcessingDataService;

@Service
public class PersistDataServiceImpl implements PersistDataService {

	private static final Logger logger = LogManager.getLogger(PersistDataServiceImpl.class);

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private FetchMetaDataService fetchMetaDataService;

	@Autowired
	private PreProcessingDataService preProcessingDataService;

	@Autowired
	private CRUDRepository repository;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private LoginService loginService;

	@Override
	public void saveReading(String reading)
			throws ServiceException, ValidationException, AuthenticationException, BadDataException {
		// 1. Parse the JSON and check whether the data is in valid format
		JsonNode root = null;
		try {
			root = mapper.readTree(reading);
		} catch (IOException e) {
			throw new BadDataException("Data received from sensor is corrupted. Request received: " + reading);
		}

		String deviceId = root.path("deviceId").asText();
//		String userId = root.path("userId").asText();
		String userId = "1"; // Hardcoding the userId for now. This will come from lookup
		JsonNode dataNode = root.path("data");
		if ((deviceId == null || deviceId.isEmpty()) || (userId == null || userId.isEmpty()) || dataNode.isMissingNode()
				|| dataNode.isNull()) {
//			logger.debug("Device ID is : " + deviceId);
//			logger.debug("User ID is : " + userId);
			logger.error("Sensor Reading received is not valid. DeviceId : " + deviceId + " User Id: " + userId);
			throw new ValidationException(
					"Sensor Reading received is not valid. DeviceId : " + deviceId + " User Id: " + userId);
		}

		// 2. Authenticate the device and user
		boolean isAuth = authenticationService.authenticateDevice(deviceId, userId);

		if (!isAuth) {
			// Device authentication failed
			logger.error("Authentication failed! DeviceId : " + deviceId + "User Id: " + userId);
			throw new AuthenticationException("Authentication failed!. DeviceId : " + deviceId + "User Id: " + userId);

		}

		// 3. Identify the device and user. Get Metadeta for the same
		UserMetaData userMetaData = fetchMetaDataService.fetchMetaData(userId);

		// Preprocess data if the user has been configured for the same
		if (userMetaData.isPreProcessing()) {
			// Do pre-processing
			root = preProcessingDataService.preProcessData(root);

		}

		// Persist the data into Elasticsearch
		repository.save(root, userMetaData);

		// Successfully completed sensor data processing and persistance

	}

//	public String fetchReading(String authHeader) throws ServiceException, ValidationException, AuthenticationException, BadDataException {
//		loginService.authUser(authHeader);
//		
//		//Call the metaDeta service
//		//Fetch the reading/s from Elasticsearch and return
//		
//		return "sensor reading";
//	}

}
