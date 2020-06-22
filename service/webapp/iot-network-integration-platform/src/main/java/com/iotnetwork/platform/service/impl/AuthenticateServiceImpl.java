package com.iotnetwork.platform.service.impl;

import org.springframework.stereotype.Service;

import com.iotnetwork.platform.service.AuthenticationService;

@Service
public class AuthenticateServiceImpl implements AuthenticationService {

	@Override
	public boolean authenticateDevice(String deviceId, String userId) {
//		if (deviceId.equals("device") && userId.equals("user")) {
//			return true;
//		}
//		return false;
		return true;
	}

}
