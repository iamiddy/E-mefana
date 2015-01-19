/**
 * 
 */
package com.idrene.emefana.domain;

import java.util.Optional;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author iddymagohe
 *
 */
@Document(collection = "users")
@TypeAlias("users")
public class User {
    
	@Id
	@Setter @Getter private String id;
	
	@Setter @Getter private String firstName;
	
	@Setter @Getter private String lastName;
	
	@Indexed
	@Setter @Getter String emailAddress;
	
	@Indexed
	@Getter @Setter String associatedProvider;
	
	@Setter @Getter String password;

	
	
	/**
	 * @param id
	 * @param emailAddress
	 * @param password
	 */
	public User(String id, String emailAddress, String password) {
		this.id = id;
		this.emailAddress = emailAddress;
		this.password = password;
	}
	
	public User(){
		
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + "]";
	}
	
	public Optional<String> getOAssociatedProvider(){
		return Optional.ofNullable(StringUtils.trimToNull(associatedProvider));
	}
	
	public Optional<String> getOemailAddress(){
		return Optional.ofNullable(emailAddress);
	}
	
	public Optional<String> getOuserId(){
		return Optional.ofNullable(id);
	}
	
	
}
