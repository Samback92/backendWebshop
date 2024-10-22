// package com.backendWebshop.backendWebshop.model;

// import java.util.Collection;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// public class UserDto implements UserDetails {
//     private String id;
//     private String fName;
//     private String lName;
//     private String email;
//     private String username;
//     private String password;
    

    
//     public UserDto(String fName, String lName, String email, String username, String password) {
//         this.fName = fName;
//         this.lName = lName;
//         this.email = email;
//         this.username = username;
//         this.password = password;
//     }

//     public UserDto(User user) {
//         this.id = user.getId();
//         this.username = user.getUsername();
//     }

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         return null;
//     }

//     public String getfName() {
//         return fName;
//     }

//     public void setfName(String fName) {
//         this.fName = fName;
//     }

//     public String getlName() {
//         return lName;
//     }

//     public void setlName(String lName) {
//         this.lName = lName;
//     }

//     public String getEmail() {
//         return email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     public void setUsername(String username) {
//         this.username = username;
//     }

//     public void setPassword(String password) {
//         this.password = password;
//     }

//     @Override
//     public String getPassword() {
//         return password;
//     }

//     @Override
//     public String getUsername() {
//         return username;
//     }

//     @Override
//     public boolean isAccountNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//         return true;
//     }

//     @Override
//     public boolean isCredentialsNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isEnabled() {
//         return true;
//     }

//     public String getId() {
//         return id;
//     }

//     public void setId(String id) {
//         this.id = id;
//     }

// }
