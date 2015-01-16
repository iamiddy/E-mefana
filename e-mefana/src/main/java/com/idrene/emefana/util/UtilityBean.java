/**
 * 
 */
package com.idrene.emefana.util;

import org.apache.commons.lang3.StringUtils;
import org.jasypt.util.text.TextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author iddymagohe
 *
 */
public class UtilityBean {

	@Autowired
	TextEncryptor textEncrptor;

	public String encodePropertyValue(String filedValue) {
		return StringUtils.trimToNull(filedValue) == null ? filedValue
				: textEncrptor.encrypt(filedValue);
	}
	
	public String decodePropertyValue(String filedValue) {
		return StringUtils.trimToNull(filedValue) == null ? filedValue
				: textEncrptor.decrypt(filedValue);
	}

}
