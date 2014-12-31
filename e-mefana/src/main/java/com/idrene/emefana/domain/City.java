/**
 * 
 */
package com.idrene.emefana.domain;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author iddymagohe
 *
 */
@Document(collection="cities")
@TypeAlias("cities")
public class City {
	@Id
	@Getter @Setter private String cid;
	
	@Field("region")
	@Getter @Setter private String region;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cid == null) ? 0 : cid.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
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
		City other = (City) obj;
		if (cid == null) {
			if (other.cid != null)
				return false;
		} else if (!cid.equalsIgnoreCase(other.cid))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equalsIgnoreCase(other.region))
			return false;
		return true;
	}
	
	

}
