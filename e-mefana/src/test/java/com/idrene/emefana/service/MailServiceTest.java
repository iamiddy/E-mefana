/**
 * 
 */
package com.idrene.emefana.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.idrene.emefana.AbstractIntegrationTest;

/**
 * @author iddymagohe
 * @since 1.0
 */
public class MailServiceTest extends AbstractIntegrationTest{
	
	@Autowired
	private MailService MailService;
	
	@Test
	public void sendMailToIddy85Test(){
		MailService.sendMail(null);
	}

}
