// package com.backendWebshop.backendWebshop.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;
// import java.util.stream.Collectors;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// import com.backendWebshop.backendWebshop.exception.ResourceNotFoundException;
// import com.backendWebshop.backendWebshop.model.User;
// import com.backendWebshop.backendWebshop.model.UserDto;
// import com.backendWebshop.backendWebshop.repository.UserRepository;

// @RestController
// @RequestMapping("/api/users")
// public class UserController {

//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     private PasswordEncoder passwordEncoder;

//     private static final Logger log = LoggerFactory.getLogger(UserController.class);

//     @GetMapping("/current")
//     public ResponseEntity<UserDto> getCurrentUser(Authentication authentication) {
//         if (authentication == null || !authentication.isAuthenticated()) {
//             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//         }
//         UserDto userDto = (UserDto) authentication.getPrincipal();
//         return ResponseEntity.ok(userDto);
//     }

//     @PostMapping("/register")
//     public String registerUser(@RequestBody UserDto userDto, RedirectAttributes r) {
//         try {
//             if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
//                 r.addFlashAttribute("error", "En användare med denna e-postadress finns redan.");
//                 return "redirect:/register";
//             }
//             if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
//                 r.addFlashAttribute("error", "En användare med detta användarnamn finns redan.");
//                 return "redirect:/register";
//             }

//             String encryptedPassword = passwordEncoder.encode(userDto.getPassword());
//             User user = new User();
//             user.setfName(userDto.getfName());
//             user.setlName(userDto.getlName());
//             user.setEmail(userDto.getEmail());
//             user.setUsername(userDto.getUsername());
//             user.setPassword(encryptedPassword);
//             userRepository.save(user);

//         } catch (Exception e) {
//             log.error("Ett fel inträffade när vi försökte skapa ditt konto: ", e);
//             r.addFlashAttribute("error", "Ett fel uppstod när vi försökte skapa ditt konto. Försök igen senare.");
//             return "redirect:/register";
//         }
//         r.addFlashAttribute("registered", "Registrerad!");
//         log.info("Du registrerade ett konto med användarnamnet " + userDto.getUsername());
//         return "redirect:/";
//     }

//     @GetMapping("/{username}")
//     public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
//         User user = userRepository.findByUsername(username)
//                                 .orElseThrow(() -> new ResourceNotFoundException("User not found"));
//         UserDto userDto = new UserDto(user);
//         return ResponseEntity.ok(userDto);
//     }

//     @GetMapping
//     public List<UserDto> getAllUsers() {
//         List<User> users = userRepository.findAll();
//         return users.stream()
//                     .map(u -> new UserDto(u))
//                     .collect(Collectors.toList());
//     }
// }