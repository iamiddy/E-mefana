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
@Document(collection="providers")
@TypeAlias("providers")
public class Provider {
	@Id
	@Getter @Setter private String pid;
	@Getter @Setter private String name;
	@Getter @Setter private double[] location;
	@Getter @Setter private String type;

}
