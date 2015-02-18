
package com.idrene.emefana.rest.resources.types;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "speed",
    "heading",
    "altitudeAccuracy",
    "accuracy",
    "altitude",
    "longitude",
    "latitude"
})
public class Location {

    @JsonProperty("speed")
    private Object speed;
    @JsonProperty("heading")
    private Object heading;
    @JsonProperty("altitudeAccuracy")
    private Object altitudeAccuracy;
    @JsonProperty("accuracy")
    private Integer accuracy;
    @JsonProperty("altitude")
    private Object altitude;
    @JsonProperty("longitude")
    private Double longitude;
    @JsonProperty("latitude")
    private Double latitude;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The speed
     */
    @JsonProperty("speed")
    public Object getSpeed() {
        return speed;
    }

    /**
     * 
     * @param speed
     *     The speed
     */
    @JsonProperty("speed")
    public void setSpeed(Object speed) {
        this.speed = speed;
    }

    /**
     * 
     * @return
     *     The heading
     */
    @JsonProperty("heading")
    public Object getHeading() {
        return heading;
    }

    /**
     * 
     * @param heading
     *     The heading
     */
    @JsonProperty("heading")
    public void setHeading(Object heading) {
        this.heading = heading;
    }

    /**
     * 
     * @return
     *     The altitudeAccuracy
     */
    @JsonProperty("altitudeAccuracy")
    public Object getAltitudeAccuracy() {
        return altitudeAccuracy;
    }

    /**
     * 
     * @param altitudeAccuracy
     *     The altitudeAccuracy
     */
    @JsonProperty("altitudeAccuracy")
    public void setAltitudeAccuracy(Object altitudeAccuracy) {
        this.altitudeAccuracy = altitudeAccuracy;
    }

    /**
     * 
     * @return
     *     The accuracy
     */
    @JsonProperty("accuracy")
    public Integer getAccuracy() {
        return accuracy;
    }

    /**
     * 
     * @param accuracy
     *     The accuracy
     */
    @JsonProperty("accuracy")
    public void setAccuracy(Integer accuracy) {
        this.accuracy = accuracy;
    }

    /**
     * 
     * @return
     *     The altitude
     */
    @JsonProperty("altitude")
    public Object getAltitude() {
        return altitude;
    }

    /**
     * 
     * @param altitude
     *     The altitude
     */
    @JsonProperty("altitude")
    public void setAltitude(Object altitude) {
        this.altitude = altitude;
    }

    /**
     * 
     * @return
     *     The longitude
     */
    @JsonProperty("longitude")
    public Double getLongitude() {
        return longitude;
    }

    /**
     * 
     * @param longitude
     *     The longitude
     */
    @JsonProperty("longitude")
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * 
     * @return
     *     The latitude
     */
    @JsonProperty("latitude")
    public Double getLatitude() {
        return latitude;
    }

    /**
     * 
     * @param latitude
     *     The latitude
     */
    @JsonProperty("latitude")
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
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
        return new HashCodeBuilder().append(speed).append(heading).append(altitudeAccuracy).append(accuracy).append(altitude).append(longitude).append(latitude).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Location) == false) {
            return false;
        }
        Location rhs = ((Location) other);
        return new EqualsBuilder().append(speed, rhs.speed).append(heading, rhs.heading).append(altitudeAccuracy, rhs.altitudeAccuracy).append(accuracy, rhs.accuracy).append(altitude, rhs.altitude).append(longitude, rhs.longitude).append(latitude, rhs.latitude).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
