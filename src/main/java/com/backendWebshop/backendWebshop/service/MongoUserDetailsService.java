// package com.backendWebshop.backendWebshop.service;

// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import com.backendWebshop.backendWebshop.model.User;
// import com.backendWebshop.backendWebshop.model.UserDto;
// import com.backendWebshop.backendWebshop.repository.UserRepository;

// @Service
// public class MongoUserDetailsService implements UserDetailsService {
//     private final UserRepository userRepository;

//     public MongoUserDetailsService(UserRepository userRepository) {
//         this.userRepository = userRepository;
//     }

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         User user = userRepository.findByUsername(username)
//                 .orElseThrow(() -> new UsernameNotFoundException("User not found"));

//         return new UserDto(user);
//     }
// }
