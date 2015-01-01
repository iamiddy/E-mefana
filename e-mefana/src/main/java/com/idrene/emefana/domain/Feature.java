/**
 * 
 */
package com.idrene.emefana.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author iddymagohe
 *
 */
public class Feature {
	@Getter @Setter private String name;
	@Getter @Setter private List<String> description;
}
