package com.iotnetwork.platform.service;

public interface AuthenticationService {
	public boolean authenticateDevice(String deviceId, String userId);
}
