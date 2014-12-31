/**
 * 
 */
package com.idrene.emefana.domain;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author iddymagohe
 *
 */
@Document(collection="service_offering")
@TypeAlias("service_offering")
public class ServiceOffering {
	
	@Id
	@Getter @Setter private String sid;
	
	@DBRef
	@Getter @Setter Set<ProviderType> providerTypes;
	

	/**
	 * @param sid
	 * @param providerTypes
	 */
	public ServiceOffering(String sid, Set<ProviderType> providerTypes) {
		this.sid = sid;
		this.providerTypes = providerTypes;
	}
	
	public ServiceOffering() {}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sid == null) ? 0 : sid.hashCode());
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
		ServiceOffering other = (ServiceOffering) obj;
		if (sid == null) {
			if (other.sid != null)
				return false;
		} else if (!sid.equals(other.sid))
			return false;
		return true;
	}

}
