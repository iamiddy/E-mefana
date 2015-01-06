/**
 * 
 */
package com.idrene.emefana.repositories;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.idrene.emefana.AbstractIntegrationTest;
import com.idrene.emefana.domain.City;
import com.idrene.emefana.domain.Contact;
import com.idrene.emefana.domain.Contact.ContactTypeEnum;
import com.idrene.emefana.domain.EventType;
import com.idrene.emefana.domain.Feature;
import com.idrene.emefana.domain.Price;
import com.idrene.emefana.domain.Provider;
import com.idrene.emefana.domain.ProviderService;
import com.idrene.emefana.domain.ProviderType;
import com.idrene.emefana.domain.ServiceOffering;
import com.idrene.emefana.util.EmefanaIDGenerator;

/**
 * @author iddymagohe
 *
 */
public class RepositoriesTest extends AbstractIntegrationTest {

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

	@Test
	public void findByIdTest() {
		City city = cityRepository.findOne("Dar es Salaam");
		assertNotNull(city);
		assertTrue("Dar es Salaam".equals(city.getRegion()));
	}

	@Test
	public void saveProviderTypeTest() {
		providerTypeRepository.deleteAll();
		List<String> types = Arrays.asList("Venues", "Bridal Salons",
				"Catering", "Cake", "Decoration", "Photography and Video",
				"Entertainment", "Audio visual services", "Car hire");
		types.forEach(type -> providerTypeRepository
				.save(new ProviderType(type)));
		List<ProviderType> pTypes = providerTypeRepository.findAll();
		assertNotNull(pTypes);
		assertTrue(types.size() == pTypes.size());
	}

	//@Test
	public void saveServicesTest() {
		offeringTypeRepository.deleteAll();
		Map<String, Set<ProviderType>> servicesMap = new HashMap<>();

		List<String> typesRandom = Arrays.asList("precede", "deliver",
				"rampant", "lewd", "lunch", "great", "contain", "offer",
				"screw", "handsomely", "snobbish", "flashy", "pencil", "man",
				"squeal", "shelf", "wound", "moon", "optimal", "carry");

		int randomsize = typesRandom.size();
		typesRandom.forEach(service -> servicesMap
				.put(service, new HashSet<>()));

		List<ProviderType> pTypes = providerTypeRepository.findAll();
		pTypes.forEach(type -> {
			int randomApp = 1 + (int) (Math.random() * 10);
			while (randomApp > 0) {
				int randomCatNumber = (int) (Math.random() * randomsize);
				String serv = typesRandom.get(randomCatNumber);
				servicesMap.get(serv).add(type);
				randomApp--;
			}
		});

		servicesMap.forEach((key, value) -> offeringTypeRepository
				.save(new ServiceOffering(key, value)));
		List<ServiceOffering> offerings = offeringTypeRepository.findAll();
		assertNotNull(offerings);
		assertTrue(randomsize == offerings.size());
	}
	
	
	   private  List<ProviderService> providerServices(String[] providerTypes){
		//System.out.println(" offerings>>");
		List<ServiceOffering> offerings = offeringTypeRepository.findByProviderTypesTypeIn(Arrays.asList(providerTypes));
		List<ProviderService> offerings_venues = offerings.stream()
				.map(offering -> new ProviderService(offering," best services ,more details to come ...", new Price(100.0,""))).collect(Collectors.toList());
		return offerings_venues;
    	
	}
	   
	   @Test
	   public void serviceByProviderTypeTest(){
		   List<ServiceOffering> offerings  = offeringTypeRepository.findByProviderTypesTypeIn(Arrays.asList("Venues"));
		  assertTrue(offerings.size() > 0);
	   }

	@Test
	public void eventTypeRetrievalTest() {
		List<EventType> events_type = eventTypeRepository.findAll();
		assertNotNull(events_type);
		assertTrue(events_type.size() > 0);
	}

	@Test 
	public void saveProviderTest(){
		providerRepository.deleteAll();
		Provider p = new Provider();
		p.setName("Ether Conference Centre");
		p.setPid(EmefanaIDGenerator.generateProviderId());
		p.setDescription("Ether is a unique Melbourne conference venue offering conference facilities, diverse meeting rooms and business spaces for a range of purposes "
				+ "including business events, conferences, meetings, training, workshops and more. Defined in Ayurvedic philosophy as the space in which everything happens,"
				+ " Ether space is unique and contemporary with modern design and furnishings throughout the 10 meeting rooms.");
        p.setLocation(new double[]{39.288764,-6.808039});
        p.getFeatures().add(new Feature("General Facilities", Arrays.asList("Accommodation Rooms", "Business Centre", "Conference Rooms")));
        p.getFeatures().add(new Feature("Catering Facilities", Arrays.asList("We have a range of catering packages to suit your event.")));
        p.getFeatures().add(new Feature("Bridal Facilities", Arrays.asList("")));
        p.getFeatures().add(new Feature("Technical Facilities", Arrays.asList("Standard audio visual equipment is available in our meeting rooms")));
        p.getFeatures().add(new Feature("Leisure Facilities", Arrays.asList("Catered","Accommodation", "Parking","Licensed","Disabled Access")));
        p.getCategories().add(providerTypeRepository.findOne("Venues"));
        p.setServices(providerServices(new String[]{"Venues"}));
		for(int i = 0; i<= 2 ; i++) p.getContacts().add(randomContact());
		providerRepository.save(p);
		Provider savedP = providerRepository.findOne(p.getPid());
		assertNotNull(savedP);
		assertTrue(p.getPid().equals(savedP.getPid()));
	}

	static Contact randomContact() {
		@SuppressWarnings("serial")
		List<Contact> contacts = new ArrayList<Contact>() {
			{
				add(new Contact(String.valueOf(ContactTypeEnum.Mobile),"869 503 998", ""));
				add(new Contact(String.valueOf(ContactTypeEnum.Telephone),"876 876 4556", ""));
				add(new Contact(String.valueOf(ContactTypeEnum.Email),"provider@e-mefana.co", "Email as all day everyday"));
				add(new Contact(String.valueOf(ContactTypeEnum.PoBox),"567 kinondoni ,Dar es Salaam ,Tanzania", ""));
			}
		};
		int random = (int) (Math.random() * (contacts.size()));
		return contacts.get(random);
	}

}
