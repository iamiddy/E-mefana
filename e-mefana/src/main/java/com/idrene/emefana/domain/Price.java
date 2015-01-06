/**
 * 
 */
package com.idrene.emefana.domain;

import java.util.Currency;

import lombok.Getter;
import lombok.Setter;

import com.idrene.emefana.config.Alocale;

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
public Price(Double price, String currency) {
	this.price = price;
	this.currency = "".equals(currency)?Currency.getInstance(Alocale.SWAHILI).getCurrencyCode():currency;

}
  
  
}
