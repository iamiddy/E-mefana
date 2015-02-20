/**
 * 
 */
package com.idrene.emefana.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idrene.emefana.rest.resources.ListingResource;

/**
 * @author iddymagohe
 * @since 1.0
 */
public interface ListingRegistrationService {
	public void registerListing(ListingResource listing);

}

@Service
class ListingRegistrationServiceImpl implements ListingRegistrationService{
	
	@Autowired
	private MailService mailService;

	@Override
	public void registerListing(ListingResource listing) {
		mailService.sendMail(listing);
	}
	
	
	
}
