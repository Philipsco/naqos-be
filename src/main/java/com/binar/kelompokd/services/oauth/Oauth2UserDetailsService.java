package com.binar.kelompokd.services.oauth;

import com.binar.kelompokd.models.entity.oauth.Users;
import com.binar.kelompokd.repos.oauth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class Oauth2UserDetailsService implements UserDetailsService{

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    Users user = userRepository.findOneByUsername(s);
    if (null == user) {
      throw new UsernameNotFoundException(String.format("Username %s is not found", s));
    }
    return user;
  }

  @CacheEvict("oauth_username")
  public void clearCache(String s) {
  }
}
