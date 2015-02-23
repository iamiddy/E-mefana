/**
 * 
 */
package com.idrene.emefana.domain;

import javax.annotation.PostConstruct;

import org.springframework.data.annotation.PersistenceConstructor;

import lombok.Getter;
import lombok.Setter;

/**
 * @author iddymagohe
 * @since 1.0
 */
public class VenuesDetail {
	
	@Getter @Setter private String name;
	@Getter @Setter private int capacity;
	@Getter @Setter Price price;
	
	public VenuesDetail(String name, int capacity, Double price, String currency) {
		this.name = name;
		this.capacity = capacity;
		this.price = new Price(price, currency);
	}

	@PersistenceConstructor
	public VenuesDetail(String name, int capacity, Price price) {
		this.name = name;
		this.capacity = capacity;
		this.price = price;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capacity;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VenuesDetail other = (VenuesDetail) obj;
		if (capacity != other.capacity)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equalsIgnoreCase(other.name))
			return false;
		return true;
	}
	
	
	
}