/**
 * 
 */
package com.idrene.emefana.domain;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author iddymagohe
 *
 */
@Document(collection="provider_type")
@TypeAlias("provider_type")
public class ProviderType {
	
	@Id
	@Getter @Setter private String type;

	/**
	 * @param type
	 */
	public ProviderType(String type) {
		this.type = type;
	}

	/**
	 * 
	 */
	public ProviderType() {
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		ProviderType other = (ProviderType) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equalsIgnoreCase(other.type))
			return false;
		return true;
	}
	
	
	
}
