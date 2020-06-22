package com.iotnetwork.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.iotnetwork.platform.service.PreProcessingDataService;
import com.iotnetwork.platform.utility.CommonUtil;
import com.iotnetwork.platform.utility.constants.ApplicationConstants;

@Service
public class PreProcessingDataServiceImpl implements PreProcessingDataService {

	@Autowired
	CommonUtil commonUtil;

	@Override
	public JsonNode preProcessData(JsonNode data) {
		// Appending date as of now
		// Get current date in proper format
		((ObjectNode) data).put("timestamp", commonUtil.getCurrentDateWithFormat(ApplicationConstants.DATE_FORMAT));
		return data;
	}

}
