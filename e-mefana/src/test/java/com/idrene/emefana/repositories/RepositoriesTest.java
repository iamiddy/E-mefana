/**
 * 
 */
package com.idrene.emefana.repositories;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.util.CollectionUtils;

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
	 static final List<String> venuesDescr = Arrays
			.asList("Jolly: Hidden in the heart of Melbourne's central business district, Henry & The Fox can cater for up to 240 guests, over two spaces - the main restaurant or the outdoor terrace. Entire venue hire is also available for exclusive functions and events.",
					"Zanaki:Artful design, breathtaking views and award-winning food and wine from acclaimed chef Shannon Bennett ensures Vue Events at Rialto is Melbourne's most impressive wedding location, catering for up to 250 pax cocktail style and 130 pax banquet style.",
					"Kariakoo:When hosting a cutting-edge event, the Forum Melbourne opposite Federation Square on Flinders Street is ideal for opening night parties, launches, fashion parades and cocktail receptions for 400-1500 guests.",
					"Tabata:Centrally located to take full advantage of all that Melbourne has to offer, Rydges on Swanston is the ideal venue for conference, banquets or business retreats",
					"Azimio:The iconic Plaza Ballroom on Collins Street with its stunning Spanish Rococo architecture is a venue unmatched in history, grandeur and ambience and is renowned for being one of Melbourne's most elegant and unique event spaces.",
					"Ukonga:Melbourne River Cruises are a unique venue option for private celebrations and special events. With a fleet of seven fully enclosed vessels, each with its own viewing deck, we are capable of catering for many sized function and event requirements",
					"Yombo Vituka:The Urban Workshop Conference Centre is easily accessible from Parliament Station. The Conference Centre has four rooms in various sizes and a range of presentation options from small boardroom meetings up to large theatrette seating for 80 attendees",
					"Hanna Nassif Dar es Salaam:Ideal for engagements, weddings, birthdays, perfect for corporate functions, wrap parties and baptisms, Masque Bar is a stunning events venue for both day and evening cocktail functions and events, overlooking vibrant Park Street in South Melbourne.",
					"Mbezi Dar es Salaam:Hidden away on Hardware Lane in Melbourne's CBD lies Golden Monkey, one of Melbourne's most unique venues. We combine the romance, mystery and glamour of Old Shanghai with the comfort and service of a world class venue. Come find us!",
					"Lugalo Dar es Salaam:Hidden away on Hardware Lane in Melbourne's CBD lies Golden Monkey, one of Melbourne's most unique venues. We combine the romance, mystery and glamour of Old Shanghai with the comfort and service of a world class venue. Come find us!",
					"CocaCola Rd Dar es Salaam:With a choice of indoor and outdoor event spaces, Hidden Alley is a new venue located near South Melbourne Market that is perfect for all types of functions and events - birthday celebrations, parties, launches, meetings and group functions.",
					"University of Dar ES Salaam:The pace of Melbourne’s CBD. The calm of Albert Park. The sophisticated setting. The Point is uniquely positioned to deliver your seamless event.",
					"Mlimani City Dar es Salaam:Novotel Melbourne St Kilda caters for a variety of corporate functions and events. Choose from 12 versatile function spaces for up to 300 delegates. Many of the rooms offer stunning ocean views and outdoor terrace areas for informal networking.",
					"Contena Dar es Salaam:Featuring panoramic views of Port Phillip Bay and Melbourne’s skyline, Lifeguards Conference & Function Centre is the perfect place for your next function. The venue is is only a short drive from Melbourne CBD, St Kilda foreshore and outer suburbs.",
					"Kawe Dar es Salaam:Ormond Hall is a perfect choice for gatherings such as weddings, engagements, birthdays or any special celebrations. Now part of the community hub of Village Melbourne, the venue offers three event spaces - the Art Deco Hall, Lounge and Courtyard.");
	 
	
		Map<String ,String> venuesDescrMap = new HashMap<>();
		
	

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

	// @Test
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

	// @Test
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

	private List<ProviderService> providerServices(String[] providerTypes) {
		// System.out.println(" offerings>>");
		List<ServiceOffering> offerings = offeringTypeRepository
				.findByProviderTypesTypeIn(Arrays.asList(providerTypes));
		List<ProviderService> offerings_venues = offerings
				.stream()
				.map(offering -> new ProviderService(offering," best services ,more details to come ...", new Price(100.0, ""))).collect(Collectors.toList());
		return offerings_venues;

	}

	@Test
	public void serviceByProviderTypeTest() {
		List<ServiceOffering> offerings = offeringTypeRepository
				.findByProviderTypesTypeIn(Arrays.asList("Venues"));
		assertTrue(offerings.size() > 0);
	}

	@Test
	public void eventTypeRetrievalTest() {
		price();
		List<EventType> events_type = eventTypeRepository.findAll();
		assertNotNull(events_type);
		assertTrue(events_type.size() > 0);
	}

	// @Test
	public void saveProviderTest() {
		providerRepository.deleteAll();
		Provider p = new Provider();
		p.setName("Ether Conference Centre");
		p.setPid(EmefanaIDGenerator.generateProviderId());
		p.setDescription("Ether is a unique Melbourne conference venue offering conference facilities, diverse meeting rooms and business spaces for a range of purposes "
				+ "including business events, conferences, meetings, training, workshops and more. Defined in Ayurvedic philosophy as the space in which everything happens,"
				+ " Ether space is unique and contemporary with modern design and furnishings throughout the 10 meeting rooms.");
		p.setLocation(new double[] { 39.288764, -6.808039 });
		p.getFeatures().add(new Feature("General Facilities", Arrays.asList("Accommodation Rooms", "Business Centre","Conference Rooms")));
		p.getFeatures().add(new Feature("Catering Facilities",Arrays.asList("We have a range of catering packages to suit your event.")));
		p.getFeatures().add(new Feature("Bridal Facilities", Arrays.asList("")));
		p.getFeatures().add(new Feature("Technical Facilities",Arrays.asList("Standard audio visual equipment is available in our meeting rooms")));
		p.getFeatures().add(new Feature("Leisure Facilities", Arrays.asList("Catered","Accommodation", "Parking", "Licensed","Disabled Access")));
		p.getCategories().add(providerTypeRepository.findOne("Venues"));
		p.setServices(providerServices(new String[] { "Venues" }));
		for (int i = 0; i <= 2; i++)
			p.getContacts().add(randomContact());
		providerRepository.save(p);
		Provider savedP = providerRepository.findOne(p.getPid());
		assertNotNull(savedP);
		assertTrue(p.getPid().equals(savedP.getPid()));
	}

//	@Test
//	public void updateDescriptions() {
//		List<Provider> prvs = providerRepository.findAll();
//		assertNotNull(prvs);
//		assertTrue(prvs.size() > 1);
//		if(prvs.stream().anyMatch(pr -> StringUtils.isEmpty(pr.getDescription()))){
//			Optional<Map<String,String>> valuesDesc = Optional.ofNullable(venuesDescrMap);
//			if(valuesDesc.isPresent()){
//				venuesDescr.stream().map(s -> s.split(":")).forEach(s->{
//					venuesDescrMap.put(s[0], s[1]);
//				});
//			}
//		
//			for (Provider p : prvs) {
//				Optional<String> description = Optional.ofNullable(p.getDescription());
//				p.setDescription(description.orElse(venuesDescrMap.get(p.getName())));
//			}
//			
//			List<Provider> savedPvrs = providerRepository.save(prvs);
//			assertFalse(savedPvrs.stream().allMatch(pr -> StringUtils.isEmpty(pr.getDescription())));
//		}
//	}
	
	@Test
	public void updateDescriptionsTest(){
			venuesDescr.stream().map(s -> s.split(":")).forEach(s -> {
				venuesDescrMap.put(s[0], s[1]);
			});
		
		List<Provider> prvs = providerRepository.findAll();
		updateProvidersLambda(prvs,
				provider -> StringUtils.isEmpty(provider.getDescription()),
				provider -> {
					Optional<String> description = Optional.ofNullable(provider.getDescription());
					provider.setDescription(description.orElse(venuesDescrMap.get(provider.getName())));
				});
	}
	
	@Test
	public void updateContacts(){
		List<Provider> prvs = providerRepository.findAll();
		updateProvidersLambda(prvs,
				provider -> CollectionUtils.isEmpty(provider.getContacts()),
				provider -> {
					for (int i = 0; i <= 2; i++) provider.getContacts().add(randomContact());
				});
	}
	
	@Test
	public void updateFeatures(){
		List<Provider> prvs = providerRepository.findAll();
		updateProvidersLambda(prvs,
				provider -> CollectionUtils.isEmpty(provider.getFeatures()),
				provider -> provider.setFeatures(randomFeature()));
	}
	
	@Test
	public void updateCapacity(){
		List<Provider> prvs = providerRepository.findAll();
		updateProvidersLambda(prvs,
				provider -> provider.getCapacity() == 0 ,
				provider -> provider.setCapacity(capacity()));
	}
	
	@Test
	public void updatePrice(){
		List<Provider> prvs = providerRepository.findAll();
		updateProvidersLambda(prvs,
				provider -> provider.getPrice() == null,
				provider -> provider.setPrice(new Price(price(),null)));
	}
	
	static int capacity(){
		return (int) (Math.random() * 300) + 100;
		
	}
	
	static Double price(){
		Random random = new Random();
		return  Double.valueOf(random.longs(600000,1000000).limit(5).findAny().getAsLong());
		
	}
	
	void updateProvidersLambda(List<Provider> providers, Predicate<Provider> predicate, Consumer<Provider> setValues){
		assertNotNull(providers);
		assertTrue(providers.size() > 1);
		if (providers.stream().anyMatch(p -> predicate.test(p))) {
			providers.forEach(p -> setValues.accept(p));
			List<Provider> savedPvrs = providerRepository.save(providers);
			assertFalse(savedPvrs.stream().allMatch(predicate));
		}
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
	
	static List<Feature> randomFeature(){
		List<List<Feature>> features= new ArrayList<>();
		List<Feature> l1 = Arrays.asList(
	    new Feature("General Facilities", Arrays.asList("Accommodation Rooms", "Business Centre","Conference Rooms")),
		new Feature("Catering Facilities",Arrays.asList("We have a range of catering packages to suit your event.")),
		new Feature("Technical Facilities",Arrays.asList("Standard audio visual equipment is available in our meeting rooms")),
		new Feature("Leisure Facilities", Arrays.asList("Catered","Accommodation", "Parking", "Licensed","Disabled Access")));
		
		List<Feature> l2 = Arrays.asList(
			    new Feature("General Facilities", Arrays.asList("Bar", "Foyer", "Outdoor Facilities")),
				new Feature("Catering Facilities",Arrays.asList("We have an American style menu that offers burgers, pizza, pasta, ribs and more. Ask as about your function needs.")),
				new Feature("Technical Facilities",Arrays.asList("Audio visual facilities can be organised on request.")),
				new Feature("Leisure Facilities", Arrays.asList("Catered","Accommodation", "Parking", "Licensed","Disabled Access")));
		
		List<Feature> l3 = Arrays.asList(
			    new Feature("General Facilities", Arrays.asList("Bar", "Breakout Rooms", "Dance Floor, Function Rooms", "Outdoor Facilities", "Restaurant.")),
				new Feature("Catering Facilities",Arrays.asList("Our event offerings afford you total flexibility, allowing you to tailor your package in accordance with the specific requirements of your event."
						, " Cocktail events featuring unique flavour stations and personalised degustation menus are only small taste")),
				new Feature("Leisure Facilities", Arrays.asList("Each of our events spaces offer complimentary WiFi, iPod connectivity, lectern, microphone, CD player and an in house sound system.",
						" The Promenade Room and Lake Room also feature complete blackout facilities."," Additional audio visual equipment can be hired on your behalf with charges being determined on individual requirements.",
						" The Point engages reputable suppliers who offer access to the most up to date equipment available.")));
		
		features.addAll(Arrays.asList(l1,l2,l3));
		int random = (int) (Math.random() * (features.size()));
		return features.get(random);
	}
	
	@Test
	public void closestestPointsToMwengeTest(){
		Point mwenge = new Point(39.229809,-6.769280);
		GeoResults<Provider> geoResults = providerRepository
				.findByLocationNear(mwenge,
						new Distance(10, Metrics.KILOMETERS));
		
		assertNotNull(geoResults);
		geoResults.forEach(gp -> System.out.println(gp.getContent().getName() + " - " + gp.getDistance()));
		assertTrue(geoResults.getAverageDistance().getValue() > 0);
	}

}
