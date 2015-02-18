
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
    "filetype",
    "filename",
    "filesize",
    "base64"
})
public class Photo {

    @JsonProperty("filetype")
    private String filetype;
    @JsonProperty("filename")
    private String filename;
    @JsonProperty("filesize")
    private Integer filesize;
    @JsonProperty("base64")
    private String base64;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The filetype
     */
    @JsonProperty("filetype")
    public String getFiletype() {
        return filetype;
    }

    /**
     * 
     * @param filetype
     *     The filetype
     */
    @JsonProperty("filetype")
    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    /**
     * 
     * @return
     *     The filename
     */
    @JsonProperty("filename")
    public String getFilename() {
        return filename;
    }

    /**
     * 
     * @param filename
     *     The filename
     */
    @JsonProperty("filename")
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * 
     * @return
     *     The filesize
     */
    @JsonProperty("filesize")
    public Integer getFilesize() {
        return filesize;
    }

    /**
     * 
     * @param filesize
     *     The filesize
     */
    @JsonProperty("filesize")
    public void setFilesize(Integer filesize) {
        this.filesize = filesize;
    }

    /**
     * 
     * @return
     *     The base64
     */
    @JsonProperty("base64")
    public String getBase64() {
        return base64;
    }

    /**
     * 
     * @param base64
     *     The base64
     */
    @JsonProperty("base64")
    public void setBase64(String base64) {
        this.base64 = base64;
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
        return new HashCodeBuilder().append(filetype).append(filename).append(filesize).append(base64).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Photo) == false) {
            return false;
        }
        Photo rhs = ((Photo) other);
        return new EqualsBuilder().append(filetype, rhs.filetype).append(filename, rhs.filename).append(filesize, rhs.filesize).append(base64, rhs.base64).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
