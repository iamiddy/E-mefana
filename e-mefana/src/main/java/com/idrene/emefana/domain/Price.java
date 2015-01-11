/**
 * 
 */
package com.idrene.emefana.domain;

import java.util.Currency;
import java.util.Optional;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.PersistenceConstructor;

import com.idrene.emefana.util.Alocale;

/**
 * @author iddymagohe
 *
 */
public class Price {
	@Getter @Setter private Double price;
	@Getter @Setter String currency;

	/**
	 * @param price
	 * @param currency
	 */
	@PersistenceConstructor
	public Price(Double price, String currency) {
		this.price = price;
		Optional<String> currencyOpt = Optional.ofNullable(currency);
		this.currency = currencyOpt.orElse(Currency.getInstance(Alocale.SWAHILI).getCurrencyCode());

	}

}
