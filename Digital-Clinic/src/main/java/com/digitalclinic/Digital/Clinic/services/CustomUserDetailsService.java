package com.digitalclinic.Digital.Clinic.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.digitalclinic.Digital.Clinic.entities.User;
import com.digitalclinic.Digital.Clinic.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 User user = userRepository.findByEmail(username)
                 .orElseThrow(() ->
                         new UsernameNotFoundException("User not found with username or email: "+ username));
		 
		 GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole().name());
		 
		 Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
		 grantedAuthoritySet.add(grantedAuthority);
		 
		 return new org.springframework.security.core.userdetails.User(user.getEmail(),
	                user.getPassword(),
	                grantedAuthoritySet);
	}

}
