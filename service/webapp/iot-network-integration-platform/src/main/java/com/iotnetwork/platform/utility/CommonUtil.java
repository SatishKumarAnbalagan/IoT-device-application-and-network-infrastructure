package com.iotnetwork.platform.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "singleton")
public class CommonUtil {

	public String getCurrentDateWithFormat(String format) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(new Date());
	}
}
