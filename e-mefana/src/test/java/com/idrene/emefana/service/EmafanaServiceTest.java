/**
 * 
 */
package com.idrene.emefana.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResults;

import com.idrene.emefana.AbstractIntegrationTest;
import com.idrene.emefana.domain.Booking;
import com.idrene.emefana.domain.Provider;
import com.idrene.emefana.domain.SearchCriteria;
import com.idrene.emefana.domain.User;
import com.idrene.emefana.repositories.PersonRepository;
import com.idrene.emefana.util.UtilityBean;

/**
 * @author iddymagohe
 *
 */
public class EmafanaServiceTest extends AbstractIntegrationTest{
	@Autowired
	UtilityBean utilBean;
	
	@Autowired
	EmefanaService service;
	
	@Autowired
	PersonRepository userRepository;
	
	
	@Test
	public void encodeDecodeTest(){
		assertEquals("Winter11!",utilBean.decodePropertyValue(utilBean.encodePropertyValue("Winter11!")));
	}
	
	@Test(expected = EntityExists.class)
	public void saveUserTest() throws EntityExists{
		userRepository.delete("testUser");
		User usr = new User("testUser","testUser@emefana.com","test_user_pss");
		service.registerUser(usr).get();
		User usr2 = new User("rtretetet","testUser@emefana.com","test_user_pss2");
		service.registerUser(usr2);
		User usr3 = new User("testUser","testUser3@emefana.com","test_user_pss3");
		service.registerUser(usr3);
		
	}
	
	@Test
	public void retrieveBookingsByUserTest(){
		Optional<List<Booking>> bookings= service.retriveUserBookings(service.findUser(new User("IDRENE",null,null)).get());
	    bookings.ifPresent(booking -> booking.forEach(System.out::println));
		assertTrue(bookings.isPresent());
	}
	
	@Test //569c2dc5-ac3d-4c30-8901-70d115047b79
	public void retrieveBookingsByProviderTest(){
		User usr = new User();
		usr.setAssociatedProvider("569c2dc5-ac3d-4c30-8901-70d115047b79");
		Optional<List<Booking>> bookings= service.retriveUserBookings(usr);
	    bookings.ifPresent(booking -> booking.forEach(System.out::println));
		assertTrue(bookings.isPresent());
	}
	
	@Test //569c2dc5-ac3d-4c30-8901-70d115047b79
	public void SearchProvidersTest() {
		SearchCriteria criteria = new SearchCriteria();
		criteria.setNearLocation(new double[] {-6.769280, 39.229809 });
		criteria.setCity("Dar es Salaam");
		criteria.setMaxDistance(20);
		criteria.setPriceFrom(600000.0);
		criteria.setPriceTo(1000000.0);
		criteria.setCapacityFrom(150);
		criteria.setProviderType("Venues");
		Optional<GeoResults<Provider>> geoResults = service
				.searchProvidersByCriteria(criteria);
		assertNotNull(geoResults);
		geoResults.get().forEach(
				gp -> System.out.println(gp.getContent().getName() + " - "
						+ gp.getDistance() + " - "
						+ gp.getContent().getCapacity() + " - "
						+ gp.getContent().getPrice().getPrice() + " - "
						+ gp.getContent().getCategories().toString()
						));
		assertTrue(geoResults.get().getAverageDistance().getValue() > 0);
	}

}
