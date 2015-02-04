/**
 * 
 */
package com.idrene.emefana.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.idrene.emefana.domain.User;
import com.idrene.emefana.repositories.PersonRepository;

/**
 * @author iddymagohe
 * @since 1.0
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PersonRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userId)throws UsernameNotFoundException {
		User account = userRepository.findByEmailAddressAllIgnoreCase(userId);
		if (account == null) {
			throw new UsernameNotFoundException("Unknown user with name  "+ userId);
		}
		return new AccountUserDetails(account);
	}

}
