package com.iotnetwork.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.iotnetwork.platform.model.meta.UserMetaData;
import com.iotnetwork.platform.service.FetchMetaDataService;

@Service
public class FetchMetaDataServiceImpl implements FetchMetaDataService {

	@Autowired
	private Environment env;

	@Override
	public UserMetaData fetchMetaData(String userId) {

		// Not the right approach. Need to cache these values in a hashmap on
		// application startup
		UserMetaData userMetaData = new UserMetaData();
		userMetaData.setIndexName(env.getProperty("ES.INDEX." + userId));
		userMetaData.setIndexType(env.getProperty("ES.TYPE." + userId));
		userMetaData.setPreProcessing(Boolean.parseBoolean(env.getProperty("ES.PROCESSING." + userId)));
		return userMetaData;
	}

}
