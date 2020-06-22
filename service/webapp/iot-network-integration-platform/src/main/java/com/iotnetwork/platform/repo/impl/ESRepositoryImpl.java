package com.iotnetwork.platform.repo.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.iotnetwork.platform.connection.ESClient;
import com.iotnetwork.platform.exception.ServiceException;
import com.iotnetwork.platform.model.meta.UserMetaData;
import com.iotnetwork.platform.repo.CRUDRepository;
import com.iotnetwork.platform.utility.constants.ApplicationConstants;

@Repository
public class ESRepositoryImpl implements CRUDRepository {

	private static final Logger logger = LogManager.getLogger(ESRepositoryImpl.class);

	public void save(JsonNode dataNode, UserMetaData userMetaData) throws ServiceException {
		try {
			logger.debug("Saving a reading to the " + userMetaData.getIndexName() + " index");
			IndexRequest request = new IndexRequest(userMetaData.getIndexName(),
					userMetaData.getIndexType() != null ? userMetaData.getIndexType() : ApplicationConstants.TYPE)
							.source(dataNode.toString(), XContentType.JSON);

			ESClient.getConnection().index(request, RequestOptions.DEFAULT);

		} catch (JsonProcessingException jsonProcessingException) {
			logger.error("Unable to parse the reading", jsonProcessingException);
			throw new ServiceException("Unable to parse the reading", jsonProcessingException);
		} catch (IOException ioException) {
			logger.error("Unable to save the reading to DB. Reading: " + dataNode.toString(), ioException);
			throw new ServiceException("Unable to save the reading to DB. Reading: " + dataNode.toString(),
					ioException);
		}

	}

}
