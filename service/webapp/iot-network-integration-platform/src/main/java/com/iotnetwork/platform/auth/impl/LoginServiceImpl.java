package com.iotnetwork.platform.auth.impl;

import org.springframework.stereotype.Service;

import com.iotnetwork.platform.auth.LoginService;


@Service
public class LoginServiceImpl implements LoginService {

//	@Autowired
//	private LoginServiceUtil loginServiceUtil;
//
//	@Autowired
//	private UserRepository userRepository;
//
//
//
//	public void authUser(String authHeader) throws ValidationException, AuthenticationException {
//		if (authHeader != NO_AUTH) {
//			byte[] bytes = loginServiceUtil.getDecodedString(authHeader.split(" ")[1]);
//
//			String userPassArr[] = new String(bytes).split(":");
//			if (userPassArr.length != 2) {
//				throw new ValidationException(EMAILID_PASSWORD_MISSING);
//			}
//
//			String emailId = userPassArr[0];
//			String password = userPassArr[1];
//			String actualPassword = "";
//			loginServiceUtil.isValidEmail(emailId);
//			List<UserCredentials> credentialList = userRepository.findByEmailId(emailId.toLowerCase());
//			if (credentialList != null && credentialList.size() == 1) {
//				actualPassword = credentialList.get(0).getPassword();
//				loginServiceUtil.verifyPassword(password, actualPassword);
//			} else {
//				throw new ResourceNotFoundException("Invalid user ID.");
//			}
//		} else {
//			throw new AuthenticationException();
//		}
//	}

}
