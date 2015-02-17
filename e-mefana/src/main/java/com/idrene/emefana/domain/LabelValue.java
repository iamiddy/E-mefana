/**
 * 
 */
package com.idrene.emefana.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author iddymagohe
 * @since 1.0
 */
public class LabelValue {
	
	@Getter @Setter private String label;
	@Getter @Setter private String value;
	
	public LabelValue(String label, String value) {
		this.label = label;
		this.value = value;
	}
	

}
