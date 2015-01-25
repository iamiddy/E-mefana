/**
 * 
 */
package com.idrene.emefana.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.jasypt.util.text.TextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.FileCopyUtils;

/**
 * @author iddymagohe
 *
 */
public class UtilityBean {

	@Autowired
	TextEncryptor textEncrptor;

	@Autowired
	PasswordEncoder passwordEncrptor;

	public String encodePropertyValue(String filedValue) {
		return StringUtils.trimToNull(filedValue) == null ? filedValue
				: textEncrptor.encrypt(filedValue);
	}

	public String decodePropertyValue(String filedValue) {
		return StringUtils.trimToNull(filedValue) == null ? filedValue
				: textEncrptor.decrypt(filedValue);
	}

	public String encryptPassword(String password) {
		return passwordEncrptor.encode(password);
	}

	public static String generateProviderId() {
		return UUID.randomUUID().toString();
	}

	public static String generateProviderCode(String codefrom) {
		return StringUtils.upperCase(RandomStringUtils.random(5,
				codefrom.replaceAll("\\s+", "")));
	}

	public static <T> ArrayList<T> toArrayList(final Iterator<T> iterator) {
		return StreamSupport.stream(
				Spliterators.spliteratorUnknownSize(iterator,
						Spliterator.ORDERED), false).collect(
				Collectors.toCollection(ArrayList::new));

	}

	public static <T> List<T> toList(final Iterable<T> iterable) {
		return StreamSupport.stream(iterable.spliterator(), false).collect(
				Collectors.toList());
	}

	public static Optional<String> InputStreamToBase64(Optional<InputStream> inputStream, String ext) throws IOException{
		if (inputStream.isPresent()) {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			FileCopyUtils.copy(inputStream.get(), output);
			return Optional.ofNullable("data:image/"+ext+";base64," + DatatypeConverter.printBase64Binary(output.toByteArray()));
		}

		return Optional.empty();
	}
	
	public static Optional<InputStream> Base64ToInputStream(Optional<String> base64String)throws IOException {
		if (base64String.isPresent()) {
			return Optional.ofNullable(new ByteArrayInputStream(DatatypeConverter.parseBase64Binary(base64String.get())));
		}

		return Optional.empty();
	}

}
