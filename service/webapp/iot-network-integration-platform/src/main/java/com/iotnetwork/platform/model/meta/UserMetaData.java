package com.iotnetwork.platform.model.meta;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class UserMetaData {

	private String indexName; //Elasticsearch IndexName configured for user
	private String indexType; //Elasticsearch IndexType configured for user
	private boolean isPreProcessing; //Flag which indicates whether data pre-processing has been enabled or no
	//Other metaData can be added here
}
