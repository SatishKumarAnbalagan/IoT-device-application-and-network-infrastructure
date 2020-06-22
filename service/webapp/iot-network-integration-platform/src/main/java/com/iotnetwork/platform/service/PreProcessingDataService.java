package com.iotnetwork.platform.service;

import com.fasterxml.jackson.databind.JsonNode;

public interface PreProcessingDataService {
	public JsonNode preProcessData(JsonNode data) ;
}
