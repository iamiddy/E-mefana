
package com.idrene.emefana.rest.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.idrene.emefana.domain.EventType;
import com.idrene.emefana.domain.ServiceOffering;
import com.idrene.emefana.rest.resources.types.Category;
import com.idrene.emefana.rest.resources.types.CityResource;
import com.idrene.emefana.rest.resources.types.FeatureResource;
import com.idrene.emefana.rest.resources.types.Location;
import com.idrene.emefana.rest.resources.types.Photo;
import com.idrene.emefana.rest.resources.types.UserResource;
import com.idrene.emefana.rest.resources.types.Venue;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "venues",
    "features",
    "services",
    "events",
    "uselocation",
    "feature",
    "agree",
    "user",
    "name",
    "category",
    "description",
    "country",
    "location",
    "city",
    "streetaddress",
    "additionalstreetaddress",
    "phonenumber",
    "photo"
})
public class ListingResource {

    /**
     * 
     */
	
    @JsonProperty("venues")
    private List<Venue> venues = new ArrayList<Venue>();
    @JsonProperty("features")
    private List<FeatureResource> features = new ArrayList<FeatureResource>();
    @JsonProperty("services")
    private List<ServiceOffering> services = new ArrayList<ServiceOffering>();
    @JsonProperty("events")
    private List<EventType> events = new ArrayList<EventType>();
    @JsonProperty("uselocation")
    private Boolean uselocation;
    @JsonProperty("feature")
    private String feature;
    @JsonProperty("agree")
    private Boolean agree;
    @JsonProperty("user")
    private UserResource user;
    @JsonProperty("name")
    private String name;
    @JsonProperty("category")
    private Category category;
    @JsonProperty("description")
    private String description;
    @JsonProperty("country")
    private String country;
    @JsonProperty("location")
    private Location location;
    @JsonProperty("city")
    private CityResource city;
    @JsonProperty("streetaddress")
    private String streetaddress;
    @JsonProperty("additionalstreetaddress")
    private String additionalstreetaddress;
    @JsonProperty("phonenumber")
    private String phonenumber;
    @JsonProperty("photo")
    private Photo photo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The venues
     */
    @Valid
    @JsonProperty("venues")
    public List<Venue> getVenues() {
        return venues;
    }

    /**
     * 
     * @param venues
     *     The venues
     */
    @JsonProperty("venues")
    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }

    /**
     * 
     * @return
     *     The features
     */
    @JsonProperty("features")
    public List<FeatureResource> getFeatures() {
        return features;
    }

    /**
     * 
     * @param features
     *     The features
     */
    @JsonProperty("features")
    public void setFeatures(List<FeatureResource> features) {
        this.features = features;
    }

    /**
     * 
     * @return
     *     The services
     */
    @NotEmpty(message = "Services can not empty")
    @JsonProperty("services")
    public List<ServiceOffering> getServices() {
        return services;
    }

    /**
     * 
     * @param services
     *     The services
     */
    @JsonProperty("services")
    public void setServices(List<ServiceOffering> services) {
        this.services = services;
    }

    /**
     * 
     * @return
     *     The events
     */
    @NotEmpty(message = "Events can not be empty")
    @JsonProperty("events")
    public List<EventType> getEvents() {
        return events;
    }

    /**
     * 
     * @param events
     *     The events
     */
    @JsonProperty("events")
    public void setEvents(List<EventType> events) {
        this.events = events;
    }

    /**
     * 
     * @return
     *     The uselocation
     */
    @JsonProperty("uselocation")
    public Boolean getUselocation() {
        return uselocation;
    }

    /**
     * 
     * @param uselocation
     *     The uselocation
     */
    @JsonProperty("uselocation")
    public void setUselocation(Boolean uselocation) {
        this.uselocation = uselocation;
    }

    /**
     * 
     * @return
     *     The feature
     */
    @JsonProperty("feature")
    public String getFeature() {
        return feature;
    }

    /**
     * 
     * @param feature
     *     The feature
     */
    @JsonProperty("feature")
    public void setFeature(String feature) {
        this.feature = feature;
    }

    /**
     * 
     * @return
     *     The agree
     */
    @JsonProperty("agree")
    public Boolean getAgree() {
        return agree;
    }

    /**
     * 
     * @param agree
     *     The agree
     */
    @JsonProperty("agree")
    public void setAgree(Boolean agree) {
        this.agree = agree;
    }

    /**
     * 
     * @return
     *     The user
     */
    @NotNull(message="represantative can not be null")
    @Valid
    @JsonProperty("user")
    public UserResource getUser() {
        return user;
    }

    /**
     * 
     * @param user
     *     The user
     */
    @JsonProperty("user")
    public void setUser(UserResource user) {
        this.user = user;
    }

    /**
     * 
     * @return
     *     The name
     */
    @NotEmpty(message = "Business name can not be empty")
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The category
     */
    @NotNull(message = "Category can not be null")
    @Valid
    @JsonProperty("category")
    public Category getCategory() {
        return category;
    }

    /**
     * 
     * @param category
     *     The category
     */
    @JsonProperty("category")
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * 
     * @return
     *     The description
     */
    @NotEmpty(message = "Decription can not be empty")
    @Length(min = 200, max =5000)
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The country
     */
    @NotEmpty
    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    /**
     * 
     * @param country
     *     The country
     */
    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 
     * @return
     *     The location
     */
    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    /**
     * 
     * @param location
     *     The location
     */
    @JsonProperty("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * 
     * @return
     *     The city
     */
    @NotNull(message = "City can not be null")
    @Valid
    @JsonProperty("city")
    public CityResource getCity() {
        return city;
    }

    /**
     * 
     * @param city
     *     The city
     */
    @JsonProperty("city")
    public void setCity(CityResource city) {
        this.city = city;
    }

    /**
     * 
     * @return
     *     The streetaddress
     */
    @NotEmpty(message = "Stree address can not be empty")
    @JsonProperty("streetaddress")
    public String getStreetaddress() {
        return streetaddress;
    }

    /**
     * 
     * @param streetaddress
     *     The streetaddress
     */
    @JsonProperty("streetaddress")
    public void setStreetaddress(String streetaddress) {
        this.streetaddress = streetaddress;
    }

    /**
     * 
     * @return
     *     The additionalstreetaddress
     */
    @JsonProperty("additionalstreetaddress")
    public String getAdditionalstreetaddress() {
        return additionalstreetaddress;
    }

    /**
     * 
     * @param additionalstreetaddress
     *     The additionalstreetaddress
     */
    @JsonProperty("additionalstreetaddress")
    public void setAdditionalstreetaddress(String additionalstreetaddress) {
        this.additionalstreetaddress = additionalstreetaddress;
    }

    /**
     * 
     * @return
     *     The phonenumber
     */
    @NotEmpty(message = "Phone number can not be empty")
    @JsonProperty("phonenumber")
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * 
     * @param phonenumber
     *     The phonenumber
     */
    @JsonProperty("phonenumber")
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    /**
     * 
     * @return
     *     The photo
     */
    @JsonProperty("photo")
    public Photo getPhoto() {
        return photo;
    }

    /**
     * 
     * @param photo
     *     The photo
     */
    @JsonProperty("photo")
    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
    
   @AssertTrue(message = "list atleast one Venue")
    public boolean isVenuesHasVenue(){
    	if(!getCategory().getType().equalsIgnoreCase("Venues")) return true;
    	return !CollectionUtils.isEmpty(getVenues());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(venues).append(features).append(services).append(events).append(uselocation).append(feature).append(agree).append(user).append(name).append(category).append(description).append(country).append(location).append(city).append(streetaddress).append(additionalstreetaddress).append(phonenumber).append(photo).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ListingResource) == false) {
            return false;
        }
        ListingResource rhs = ((ListingResource) other);
        return new EqualsBuilder().append(venues, rhs.venues).append(features, rhs.features).append(services, rhs.services).append(events, rhs.events).append(uselocation, rhs.uselocation).append(feature, rhs.feature).append(agree, rhs.agree).append(user, rhs.user).append(name, rhs.name).append(category, rhs.category).append(description, rhs.description).append(country, rhs.country).append(location, rhs.location).append(city, rhs.city).append(streetaddress, rhs.streetaddress).append(additionalstreetaddress, rhs.additionalstreetaddress).append(phonenumber, rhs.phonenumber).append(photo, rhs.photo).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
