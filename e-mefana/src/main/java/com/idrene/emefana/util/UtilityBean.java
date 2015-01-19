/**
 * 
 */
package com.idrene.emefana.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.jasypt.util.password.PasswordEncryptor;
import org.jasypt.util.text.TextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author iddymagohe
 *
 */
public class UtilityBean {

	@Autowired
	TextEncryptor textEncrptor;
	
	@Autowired
	PasswordEncryptor passwordEncrptor;

	public String encodePropertyValue(String filedValue) {
		return StringUtils.trimToNull(filedValue) == null ? filedValue
				: textEncrptor.encrypt(filedValue);
	}
	
	public String decodePropertyValue(String filedValue) {
		return StringUtils.trimToNull(filedValue) == null ? filedValue
				: textEncrptor.decrypt(filedValue);
	}
	
	public String encryptPassword(String password){
		return passwordEncrptor.encryptPassword(password);
	}
	
	public boolean checkPassword(String plainPassword, String encryptedPassword){
		return passwordEncrptor.checkPassword(plainPassword, encryptedPassword);
	}
	
	public static String generateProviderId(){
		return UUID.randomUUID().toString();
	}
	
	public static String generateProiderCode(String codefrom){
		return StringUtils.upperCase(RandomStringUtils.random(5, codefrom.replaceAll("\\s+", "")));
	}
	
	public static <T> ArrayList<T> toArrayList(final Iterator<T> iterator) {
	    return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false)
	                        .collect(Collectors.toCollection(ArrayList::new));
	    
	}
	
	public static <T> List<T> toList(final Iterable<T> iterable) {
	    return StreamSupport.stream(iterable.spliterator(),false)
	                        .collect(Collectors.toList());
	}

}
