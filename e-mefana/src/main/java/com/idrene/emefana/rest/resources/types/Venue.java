
package com.idrene.emefana.rest.resources.types;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
    "name",
    "capacity",
    "price",
    "currency"
})
public class Venue {

    @JsonProperty("name")
    private String name;
    @JsonProperty("capacity")
    private int capacity;
    @JsonProperty("price")
    private int price;
    @JsonProperty("currency")
    private String currency;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The name
     */
    @NotEmpty(message = "Venue name can not be empty")
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
     *     The capacity
     */
    @NotNull
    @Min(1)
    @JsonProperty("capacity")
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * 
     * @param capacity
     *     The capacity
     */
    @JsonProperty("capacity")
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * 
     * @return
     *     The price
     */
    @NotNull
    @Min(20)
    @JsonProperty("price")
    public Integer getPrice() {
        return price;
    }

    /**
     * 
     * @param price
     *     The price
     */
    @JsonProperty("price")
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * 
     * @return
     *     The currency
     */
    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    /**
     * 
     * @param currency
     *     The currency
     */
    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
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
        return new HashCodeBuilder().append(name).append(capacity).append(price).append(currency).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Venue) == false) {
            return false;
        }
        Venue rhs = ((Venue) other);
        return new EqualsBuilder().append(name, rhs.name).append(capacity, rhs.capacity).append(price, rhs.price).append(currency, rhs.currency).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
