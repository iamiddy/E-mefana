/**
 * 
 */
package com.idrene.emefana.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.OptionalInt;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;

import com.idrene.emefana.util.DateConvertUtil;

/**
 * @author iddymagohe
 *
 */
public class SearchCriteria {
	@Getter @Setter private Date fromDate;
	@Getter @Setter private Date toDate;
	@Getter @Setter private double[] nearLocation;
	@Getter @Setter private Double priceFrom;
	@Getter @Setter private Double priceTo;
	@Getter @Setter private String city;
	@Getter @Setter private int capacityFrom;
	@Getter @Setter private int capacityTo;
	@Getter @Setter private String[] features; 
	@Getter @Setter private String[] services;
	@Getter @Setter private String providerType;//category
	@Setter @Getter private Pageable page;
	@Setter @Getter private int maxDistance = 15;
	
	public Optional<LocalDate> getOFromDate(){
		return Optional.ofNullable(DateConvertUtil.asLocalDate(fromDate));
	}
	
	public Optional<LocalDate> getOToDate(){
		return Optional.ofNullable(DateConvertUtil.asLocalDate(toDate));
	}
	
	public Optional<double[]> getONearLocation(){
		return Optional.ofNullable(nearLocation);
	}
	
	public Optional<Double> getOpriceFrom(){
		return Optional.ofNullable(priceFrom);
	}
	
	public Optional<Double> getOpriceTo(){
		return Optional.ofNullable(priceTo);
	}
	
	public Optional<String> getOCity(){
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
		return Optional.ofNullable(StringUtils.trimToNull(providerType));
	}
}

