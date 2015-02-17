/**
 * 
 */
package com.idrene.emefana.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author iddymagohe
 * @since 1.0
 */
public class MetaResource<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String key;
	
	private List<T> value;

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @return the value
	 */
	public List<T> getValue() {
		return value;
	}

	public MetaResource(String key, List<T> value) {
		this.key = key;
		this.value = value;
	}
	
}
