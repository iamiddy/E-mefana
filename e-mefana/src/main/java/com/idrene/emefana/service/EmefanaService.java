/**
 * 
 */
package com.idrene.emefana.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResults;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.idrene.emefana.domain.Booking;
import com.idrene.emefana.domain.Provider;
import com.idrene.emefana.domain.QBooking;
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
import com.idrene.emefana.security.EMEFANA_ROLES;
import com.idrene.emefana.util.DateConvertUtil;
import com.idrene.emefana.util.UtilityBean;
import com.mysema.query.BooleanBuilder;


/**
 * @author iddymagohe
 * @since 1.0
 */
public interface EmefanaService {

	public Optional<GeoResults<Provider>> searchProvidersByCriteria(SearchCriteria criteria);

	public Optional<Provider> findProviderById(String providerId);

	public Optional<User> findUser(User user);

	public Optional<List<Booking>> retriveUserBookings(User user);

	public Optional<User> registerUser(User user) throws EntityExists;
	public Optional<Provider> registerProvider(Provider provider) throws EntityExists;

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
	private UtilityBean utilityBean;

	@Override
	public Optional<GeoResults<Provider>> searchProvidersByCriteria(SearchCriteria criteria) {
		Assert.notNull(criteria, "Criteria must not be null");
		List<String> bookiedProviders = bookingsByDates(criteria);
		return Optional.ofNullable(providerRepository.findAllProviders(criteria, Optional.ofNullable(bookiedProviders)));
	}

	@Override
	public Optional<Provider> findProviderById(String providerId) {
		Assert.notNull(providerId, "providerId must not be null");
		Provider provider = providerRepository.findOne(providerId);
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
		final QBooking qbooking = QBooking.booking;
		BooleanBuilder bookingCriteria = new BooleanBuilder();
		user.getOAssociatedProvider().ifPresent(provider -> {
			Provider dbProvider = providerRepository.findOne(provider);
					bookingCriteria.and(qbooking.provider.eq(dbProvider)).or(
							qbooking.provider.parent.eq(dbProvider));
				});

		if (!user.getOAssociatedProvider().isPresent()) {
			user.getOuserId().ifPresent(
					userId -> bookingCriteria.and(qbooking.customer
							.eq(user)));
		}
		Iterable<Booking> bookings = bookingRepository.findAll(bookingCriteria.getValue());
		return Optional.ofNullable(UtilityBean.toList(bookings));
	}

	/* (non-Javadoc)
	 * @see com.idrene.emefana.service.EmefanaService#registerUser(com.idrene.emefana.domain.User)
	 * Provider user's will have provider specific accounts, their personal account is required for personal use(not provider related)
	 */
	@Override
	public Optional<User> registerUser(User user) throws EntityExists{
		Assert.isTrue(user.getOemailAddress().isPresent() && user.getOuserId().isPresent(),"UserId and Email are mandatory fields");
		Optional<User> dbUser=Optional.ofNullable(userRepository.findByIdOrEmailAddressAllIgnoreCase(user.getId(), user.getEmailAddress()));
		if(dbUser.isPresent()) throw new EntityExists(user + " exists");
		user.setPassword(utilityBean.encryptPassword(user.getPassword()));
		user.addRoles(EMEFANA_ROLES.USER);
		dbUser = Optional.of(userRepository.save(user));
		return dbUser;
	}

	@Override
	public Optional<Provider> registerProvider(Provider provider) throws EntityExists {
		Assert.notNull(provider, "Provider must not be null");
		Optional<Provider> dbProvider = Optional.ofNullable(providerRepository.findByNameIgnoreCase(provider.getName()));
		if(dbProvider.isPresent()) throw new EntityExists(provider.getName() + " exists");
		provider.setPid(UtilityBean.generateProviderId());
		provider.setCode(UtilityBean.generateProiderCode(provider.getPid()));
		dbProvider = Optional.of(providerRepository.save(provider));
		return  dbProvider;
	}
	
	private List<String> bookingsByDates(SearchCriteria criteria){
		final QBooking qbooking = QBooking.booking;
		BooleanBuilder bookingCriteria = new BooleanBuilder();
		criteria.getOFromDate()
				.ifPresent(
						from -> {
							LocalDate todate = criteria.getOToDate()
									.isPresent() ? criteria.getOToDate().get()
									: criteria.getOFromDate().get();
							bookingCriteria.and(qbooking.startDate
									.eq(DateConvertUtil.asUtilDate(from))
									.or(qbooking.startDate.between(
											DateConvertUtil.asUtilDate(from),
											DateConvertUtil.asUtilDate(todate))));
						});
		Iterable<Booking> bookedProviders = bookingRepository.findAll(bookingCriteria.getValue());
		List<String> providerIds = new ArrayList<>() ;
		bookedProviders.forEach(booking -> providerIds.add(booking.getProvider().getPid()));
		return providerIds;
	}
	

}