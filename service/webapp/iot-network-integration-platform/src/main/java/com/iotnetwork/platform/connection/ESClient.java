package com.iotnetwork.platform.connection;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.iotnetwork.platform.utility.constants.ApplicationConstants;

@Component
public class ESClient {
	// Need to refactor

	private static String HOST; // Connect to multiple hosts
	private static int PORT_ONE;
	// private static int PORT_TWO;
	// private static String SCHEME = ApplicationConstants.SCHEME;
	private static String SCHEME;

	// Singleton instance of the RestHighLevelClient
	private static RestHighLevelClient restHighLevelClient;

	private ESClient() {

	}

	@Value("${ES.HOST}")
	public void setHost(String host) {
		HOST = host;
	}

	@Value("${ES.PORT.ONE}")
	public void setPortOne(String portOne) {
		PORT_ONE = Integer.parseInt(portOne);
	}

	@Value("${ES.HTTP.SCHEME}")
	public void setScheme(String scheme) {
		SCHEME = scheme;
	}

//	@Value("${ES.PORT.TWO}")
//	public void setPortTwo(String portTwo) {
//		PORT_TWO = Integer.parseInt(portTwo);
//	}

	public static RestHighLevelClient getConnection() {

		// Handle multiple client connections for ES servers

		if (restHighLevelClient == null) {

			synchronized (ESClient.class) {
				if (restHighLevelClient == null) {
					restHighLevelClient = new RestHighLevelClient(
							RestClient.builder(new HttpHost(HOST, PORT_ONE, SCHEME)));
				}
			}

		}

		return restHighLevelClient;
	}

	public static synchronized void closeConnection() throws IOException {
		restHighLevelClient.close();
		restHighLevelClient = null;
	}

}
