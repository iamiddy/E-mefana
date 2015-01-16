/**
 * 
 */
package com.idrene.emefana.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.idrene.emefana.AbstractIntegrationTest;
import com.idrene.emefana.util.UtilityBean;

/**
 * @author iddymagohe
 *
 */
public class EmafanaServiceTest extends AbstractIntegrationTest{
	@Autowired
	UtilityBean utilBean;
	
	@Test
	public void encodeDecodeTest(){
		assertEquals("Winter11!",utilBean.decodePropertyValue(utilBean.encodePropertyValue("Winter11!")));
	}

}
