package com.iotnetwork.platform.service;

import com.iotnetwork.platform.model.meta.UserMetaData;

public interface FetchMetaDataService {
	public UserMetaData fetchMetaData(String userId);
}
