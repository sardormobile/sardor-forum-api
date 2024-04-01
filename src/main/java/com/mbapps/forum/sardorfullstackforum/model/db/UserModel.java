package com.mbapps.forum.sardorfullstackforum.model.db;

import com.mbapps.forum.sardorfullstackforum.enums.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@Table(name = "ForumUser")
public class UserModel implements UserDetails {
  //    @Id
//    @Column(name = "userId")
//    @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer userId;
  //    @Column(nullable = false)
  private String firstName;
  //    @Column(nullable = false)
  private String lastName;
  //    @Column(nullable = false, unique = true)
  private String username;
  //    @Column(nullable = false)
  private String password;
  private String token;
  //    @Enumerated(EnumType.STRING)
  private Role role = Role.USER;

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

}

