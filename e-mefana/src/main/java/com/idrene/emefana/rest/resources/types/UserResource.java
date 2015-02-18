
package com.idrene.emefana.rest.resources.types;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;
import javax.validation.Valid;
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
    "firstname",
    "lastname",
    "emailaddress",
    "role"
})
public class UserResource {

    @JsonProperty("firstname")
    private String firstname;
    @JsonProperty("lastname")
    private String lastname;
    @JsonProperty("emailaddress")
    private String emailaddress;
    @JsonProperty("role")
    private Role role;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The firstname
     */
    @NotEmpty(message = "first name can not be null")
    @JsonProperty("firstname")
    public String getFirstname() {
        return firstname;
    }

    /**
     * 
     * @param firstname
     *     The firstname
     */
    @JsonProperty("firstname")
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * 
     * @return
     *     The lastname
     */
    @NotEmpty(message = "last name can not be null")
    @JsonProperty("lastname")
    public String getLastname() {
        return lastname;
    }

    /**
     * 
     * @param lastname
     *     The lastname
     */
    @JsonProperty("lastname")
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * 
     * @return
     *     The emailaddress
     */
    @NotEmpty(message = "email can not be null")
    @JsonProperty("emailaddress")
    public String getEmailaddress() {
        return emailaddress;
    }

    /**
     * 
     * @param emailaddress
     *     The emailaddress
     */
    @JsonProperty("emailaddress")
    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    /**
     * 
     * @return
     *     The role
     */
    @NotNull(message = "Role can not be null")
    @Valid
    @JsonProperty("role")
    public Role getRole() {
        return role;
    }

    /**
     * 
     * @param role
     *     The role
     */
    @JsonProperty("role")
    public void setRole(Role role) {
        this.role = role;
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
        return new HashCodeBuilder().append(firstname).append(lastname).append(emailaddress).append(role).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof UserResource) == false) {
            return false;
        }
        UserResource rhs = ((UserResource) other);
        return new EqualsBuilder().append(firstname, rhs.firstname).append(lastname, rhs.lastname).append(emailaddress, rhs.emailaddress).append(role, rhs.role).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
