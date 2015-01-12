/**
 * 
 */
package com.idrene.emefana.domain;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * @author iddymagohe
 *
 */
public class ProviderEvents {

	@DBRef
	@Getter
	@Setter
	private EventType event;

	@Getter
	@Setter
	private String description;

	/**
	 * @param event
	 * @param description
	 */
	@PersistenceConstructor
	public ProviderEvents(EventType event, String description) {
		this.event = event;
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProviderEvents other = (ProviderEvents) obj;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		return true;
	}
	
	

}
