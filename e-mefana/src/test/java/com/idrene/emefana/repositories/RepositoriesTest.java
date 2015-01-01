/**
 * 
 */
package com.idrene.emefana.repositories;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.idrene.emefana.AbstractIntegrationTest;
import com.idrene.emefana.domain.City;
import com.idrene.emefana.domain.EventType;
import com.idrene.emefana.domain.ProviderType;
import com.idrene.emefana.domain.ServiceOffering;

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

	@Test
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

	@Test
	public void eventTypeRetrievalTest() {
		List<EventType> events_type = eventTypeRepository.findAll();
		assertNotNull(events_type);
		assertTrue(events_type.size() > 0);
	}

}
