
package com.idrene.emefana.rest.resources.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "cid",
    "region",
    "country",
    "location"
})
public class CityResource {

    @JsonProperty("cid")
    private String cid;
    @JsonProperty("region")
    private String region;
    @JsonProperty("country")
    private String country;
    @JsonProperty("location")
    private List<Double> location = new ArrayList<Double>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The cid
     */
    @NotEmpty(message = "City can not be empty")
    @JsonProperty("cid")
    public String getCid() {
        return cid;
    }

    /**
     * 
     * @param cid
     *     The cid
     */
    @JsonProperty("cid")
    public void setCid(String cid) {
        this.cid = cid;
    }

    /**
     * 
     * @return
     *     The region
     */
    @NotEmpty
    @JsonProperty("region")
    public String getRegion() {
        return region;
    }

    /**
     * 
     * @param region
     *     The region
     */
    @JsonProperty("region")
    public void setRegion(String region) {
        this.region = region;
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
    @NotEmpty
    @JsonProperty("location")
    public List<Double> getLocation() {
        return location;
    }

    /**
     * 
     * @param location
     *     The location
     */
    @JsonProperty("location")
    public void setLocation(List<Double> location) {
        this.location = location;
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
        return new HashCodeBuilder().append(cid).append(region).append(country).append(location).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CityResource) == false) {
            return false;
        }
        CityResource rhs = ((CityResource) other);
        return new EqualsBuilder().append(cid, rhs.cid).append(region, rhs.region).append(country, rhs.country).append(location, rhs.location).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
