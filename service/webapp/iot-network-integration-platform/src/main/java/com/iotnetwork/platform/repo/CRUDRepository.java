package com.iotnetwork.platform.repo;

import com.fasterxml.jackson.databind.JsonNode;
import com.iotnetwork.platform.exception.ServiceException;
import com.iotnetwork.platform.model.meta.UserMetaData;

public interface CRUDRepository {

	public void save(JsonNode dataNode, UserMetaData userMetaData) throws ServiceException;

}
