/**
 * 
 */
package com.idrene.emefana.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import java.util.OptionalInt;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.domain.Pageable;

/**
 * @author iddymagohe
 *
 */
public class SearchCriteria {
	@Getter @Setter private Date fromDate;
	@Getter @Setter private Date toDate;
	@Getter @Setter private double[] nearLocation;
	@Getter @Setter private BigDecimal priceFrom;
	@Getter @Setter private BigDecimal priceTo;
	@Getter @Setter private City city;
	@Getter @Setter private int capacityFrom;
	@Getter @Setter private int capacityTo;
	@Getter @Setter private String[] features;
	@Getter @Setter private String[] services;
	@Getter @Setter private String providerType;
	@Setter @Getter private Pageable page;
	
	public Optional<Date> getOFromDate(){
		return Optional.ofNullable(fromDate);
	}
	
	public Optional<Date> getOToDate(){
		return Optional.ofNullable(toDate);
	}
	
	public Optional<double[]> getONearLocation(){
		return Optional.ofNullable(nearLocation);
	}
	
	public Optional<BigDecimal> getOpriceFrom(){
		return Optional.ofNullable(priceFrom);
	}
	
	public Optional<BigDecimal> getOpriceTo(){
		return Optional.ofNullable(priceTo);
	}
	
	public Optional<City> getOCity(){
		return Optional.ofNullable(city);
	}
	
	public OptionalInt getOCapacityFrom(){
		return OptionalInt.of(capacityFrom);
	}
	
	public OptionalInt getOCapacityTo(){
		return OptionalInt.of(capacityTo);
	}
	
	public Optional<String[]> getOFeatures(){
		return Optional.ofNullable(features);
	}
	
	public Optional<String[]> getOServices(){
		return Optional.ofNullable(services);
	}
	
	public Optional<String> getOProviderType(){
		return Optional.ofNullable(providerType);
	}
}

