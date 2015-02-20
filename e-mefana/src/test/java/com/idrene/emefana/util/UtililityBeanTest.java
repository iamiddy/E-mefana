/**
 * 
 */
package com.idrene.emefana.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.idrene.emefana.AbstractIntegrationTest;

/**
 * @author iddymagohe
 * @since 1.0
 */
public class UtililityBeanTest extends AbstractIntegrationTest{
	
	@Autowired
	UtilityBean utilityBean;
	
	//@Test
	public void InputStreamToBase64Test() throws IOException{
		String base64 = UtilityBean.InputStreamToBase64(Optional.ofNullable(resource.getInputStream()), "jpg").get();
		assertNotNull(base64);
		assertTrue(base64.contains("data:image/jpg;base64")); 
	}
	
	//@Test
	public void Base64ToInputStreamTest() throws IOException{
		String base64 = UtilityBean.InputStreamToBase64(Optional.ofNullable(resource.getInputStream()), "jpg").get();
		assertNotNull(base64);
		String splitedBase64 = base64.split(",")[1];
		assertNotNull(splitedBase64);
		Optional<InputStream> content = UtilityBean.Base64ToInputStream(Optional.of(splitedBase64));
		assertTrue(content.isPresent());
		assertTrue(isInputStreamsEqual(content.get(),resource.getInputStream()));
	}
	
	
	
	private static  boolean isInputStreamsEqual(InputStream i1, InputStream i2) throws IOException {
	    byte[] buf1 = new byte[64 *1024];
	    byte[] buf2 = new byte[64 *1024];
	    try {
	        DataInputStream d2 = new DataInputStream(i2);
	        int len;
	        while ((len = i1.read(buf1)) > 0) {
	            d2.readFully(buf2,0,len);
	            for(int i=0;i<len;i++)
	              if(buf1[i] != buf2[i]) return false;
	        }
	        return d2.read() < 0; // is the end of the second file also.
	    } catch(EOFException ioe) {
	        return false;
	    } finally {
	        i1.close();
	        i2.close();
	    }
	}
	
	@Test
	public void phoneNumberMatcher(){
		String pattern = "^(\\+|0)[0-9]{7,14}$";
		String[] phones = {"0756354180","+255756354180","+14106354180"};
		for(String p : phones){
			assertTrue(p.matches(pattern));
		}
		
	}
	
	@Test
	public void testEncodeDecode(){
		String encoded = utilityBean.encodePropertyValue("OmG");
		assertTrue("OmG".equals(utilityBean.decodePropertyValue(encoded)));
		//System.out.println(utilityBean.decodePropertyValue("0OHCtQvIADiGuMnJNkJpimbLgiU1Y1Oc"));
	}
	
	
}
