/**
 * 
 */
package com.idrene.emefana.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.idrene.emefana.domain.Booking;
import com.idrene.emefana.domain.Provider;
import com.idrene.emefana.domain.QUser;
import com.idrene.emefana.domain.SearchCriteria;
import com.idrene.emefana.domain.User;
import com.idrene.emefana.repositories.BookingRepository;
import com.idrene.emefana.repositories.CityRepository;
import com.idrene.emefana.repositories.EventTypeRepository;
import com.idrene.emefana.repositories.PersonRepository;
import com.idrene.emefana.repositories.ProviderRepository;
import com.idrene.emefana.repositories.ProviderTypeRepository;
import com.idrene.emefana.repositories.ServiceOfferingRepository;
import com.idrene.emefana.util.UtilityBean;
import com.mysema.query.BooleanBuilder;

/**
 * @author iddymagohe
 *
 */
public interface EmefanaService {

	public Optional<List<Provider>> searchProvidersByCriteria(SearchCriteria criteria, User user);

	public Optional<Provider> findProviderById(String providerId, User user);

	public Optional<User> findUser(User user);

	public Optional<List<Booking>> retriveUserBookings(User user);

	public Optional<User> registerUser(User user);

}

@Service
class EmefanaServiceImpl implements EmefanaService {
	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private ProviderTypeRepository providerTypeRepository;

	@Autowired
	private ServiceOfferingRepository offeringTypeRepository;

	@Autowired
	private EventTypeRepository eventTypeRepository;

	@Autowired
	private ProviderRepository providerRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private PersonRepository userRepository;

	@Autowired
	private UtilityBean utilBean;

	@Override
	public Optional<List<Provider>> searchProvidersByCriteria(SearchCriteria criteria, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Provider> findProviderById(String providerId, User user) {
		Assert.notNull(providerId, "providerId must not be null");
		Provider provider = providerRepository.findOne(utilBean.decodePropertyValue(providerId));
		return Optional.ofNullable(provider);
	}

	@Override
	public Optional<User> findUser(User user) {
	    Assert.isTrue(user.getOemailAddress().isPresent() || user.getOuserId().isPresent(),"userId or emailAddresss must not be empty");
		QUser quser = QUser.user;
		BooleanBuilder userCriteria = new BooleanBuilder(quser.password.eq(""));
		user.getOuserId().ifPresent(id -> userCriteria.and(quser.id.eq(id)));
		user.getOemailAddress().ifPresent(email -> userCriteria.and(quser.emailAddress.eq(email)));
		User dbUser = userRepository.findOne(userCriteria.getValue());
		return Optional.ofNullable(dbUser);
	}

	@Override
	public Optional<List<Booking>> retriveUserBookings(User user) {
		
		return null;
	}

	@Override
	public Optional<User> registerUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}