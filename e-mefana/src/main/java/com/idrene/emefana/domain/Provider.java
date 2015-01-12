/**
 * 
 */
package com.idrene.emefana.domain;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
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
	
	@GeoSpatialIndexed(type=GeoSpatialIndexType.GEO_2DSPHERE)
	@Getter @Setter private double[] location;
	
	@DBRef
	@Getter @Setter private Set<ProviderType> categories = new HashSet<>();
	
	@Getter @Setter private int capacity;
	
	@Getter @Setter private Price price;
	
	@Getter @Setter private Address address;
	
	@Getter @Setter private String hours;
	
	@Getter @Setter private Set<ProviderEvents> events = new HashSet<>();
	
	@Getter @Setter private List<Feature> features = new LinkedList<>();
	
	@Getter @Setter private List<Contact> contacts = new LinkedList<>();
	
	@Getter @Setter private List<ProviderService> services = new LinkedList<>();
	
	@TextScore 
	@Getter @Setter Float score;
}
