/**
 * 
 */
package com.idrene.emefana.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

/**
 * @author iddymagohe
 *
 */
@Document(collection="providers")
@TypeAlias("providers")
public class Provider {
	@Id
	@Getter @Setter private String pid;
	
	@DBRef
	@Getter @Setter Provider parent;
	
	@TextIndexed(weight = 5)
	@Getter @Setter private String name;
	
	@TextIndexed(weight = 2)
	@Getter @Setter private String description;
	
	@Indexed
	@Getter @Setter private double[] location;
	
	@DBRef
	@Getter @Setter private Set<ProviderType> categories;
	
	@Getter @Setter private int capacity;
	
	@Getter @Setter private Price price;
	
	@Getter @Setter private String address;
	
	@DBRef
	@Getter @Setter private City city;
	
	@Getter @Setter private String hours;
	
	
	@Getter @Setter private List<ProviderEvents> events;
	
	@Getter @Setter private List<Feature> features = new LinkedList<>();
	
	@Getter @Setter private List<Contact> contacts;
	
	@Getter @Setter private List<ProviderService> services = new LinkedList<>();
	
	@TextScore 
	@Getter @Setter Float score;
	
}
