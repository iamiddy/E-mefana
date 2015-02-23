/**
 * 
 */
package com.idrene.emefana.service.request.converter;

import java.util.Arrays;

import org.springframework.core.convert.converter.Converter;

import com.idrene.emefana.domain.Address;
import com.idrene.emefana.domain.City;
import com.idrene.emefana.domain.Provider;
import com.idrene.emefana.domain.ProviderType;
import com.idrene.emefana.domain.User;
import com.idrene.emefana.rest.resources.ListingResource;
import com.idrene.emefana.security.EMEFANA_ROLES;

/**
 * @author iddymagohe
 * @since 1.0
 */
public class ListingResourceToProvider implements Converter<ListingResource, Provider> {

	@Override
	public Provider convert(ListingResource resource) {
		Provider provider = new Provider();
		provider.setName(resource.getName());
		provider.setDescription(resource.getDescription());
		provider.getCategories().add(new ProviderType(resource.getCategory().getType()));//TODO this could be Array from resource
		provider.setAddress(extractAddress(resource));
		provider.setLocation(extractLocation(resource,provider.getAddress().getCity().getLocation()));
		provider.setProviderUser(extractProviderUser(resource));
		return provider;
	}
	
	/**
	 * @param resource
	 * @return
	 */
	private Address extractAddress(ListingResource resource){
		double[] location = new double[2];
		location[0] =resource.getCity().getLocation().get(0);
		location[1] =resource.getCity().getLocation().get(1);
		City city = new City();
		city.setCid(resource.getCity().getCid());
		city.setLocation(location);
		Address address = new Address(resource.getStreetaddress(),city);
		address.setStreetLine2(resource.getAdditionalstreetaddress());
		return address;
		
	}
	
	/**
	 * @param resource
	 * @param defaultLocation
	 * @return
	 */
	private double[] extractLocation(ListingResource resource, double[] defaultLocation){
		double[] location = new double[2];
		if(resource.getUselocation()){
			location[0] = resource.getLocation().getLatitude();
			location[1] = resource.getLocation().getLongitude();
		}else{
			location = defaultLocation;
		}
		return location;
	}
	
	
	/**
	 * TODO To be persisted after provider(providerId)
	 * @param resource
	 * @return
	 */
	private User extractProviderUser(ListingResource resource){
		User usr = new User();
		usr.setEmailAddress(resource.getUser().getEmailaddress());
		usr.setFirstName(resource.getUser().getFirstname());
		usr.setLastName(resource.getUser().getLastname());
		usr.getRoles().addAll(Arrays.asList(EMEFANA_ROLES.PROVIDER,EMEFANA_ROLES.PROVIDER_USER));
		return usr;
	}

}
