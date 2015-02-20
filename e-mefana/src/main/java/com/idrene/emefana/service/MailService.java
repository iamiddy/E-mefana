/**
 * 
 */
package com.idrene.emefana.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.idrene.emefana.rest.resources.ListingResource;

/**
 * @author iddymagohe
 * @since 1.0
 */
public interface MailService {
	public void sendMail(ListingResource provider) ;

}

@Service
class MailServiceImpl implements MailService{
	
	@Autowired
	private JavaMailSenderImpl sender;

	@Override
	public void sendMail(ListingResource provider)  {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		//TODO work with Velocity template
		
		try {
			helper.setTo("iddyiam85@gmail.com");
			helper.setText("Thank you for Registering with Emefana! test");
			helper.setSubject("Emefana Hiyoooo");
		} catch (MessagingException e) {
			// TODO log this exception
			//Notify listeners for dbLogging & later re-try
			e.printStackTrace();
		}
		sender.send(message);
	}
	
}
