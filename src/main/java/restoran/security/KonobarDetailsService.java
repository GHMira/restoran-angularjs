package restoran.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import restoran.model.Konobar;
import restoran.repository.KonobarRepository;

@Service
public class KonobarDetailsService implements UserDetailsService {

    @Autowired
    private KonobarRepository konobarRepository;
   

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // first try loading from User table
        Konobar user = konobarRepository.findByUsername(username);
        if (user != null) {
            return new CustomUserDetails(user.getUsername(), user.getPass(),"USER");
        } 
        throw new UsernameNotFoundException("User '" + username + "' not found");
    }

    public class CustomUserDetails implements UserDetails {

        private String username;
        private String password;
        private Collection<? extends GrantedAuthority> authorities;

        public CustomUserDetails() {
            super();
        }

        public CustomUserDetails(String username, String password, String role) {
            this.username = username;
            this.password = password;
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(role));
            this.authorities = grantedAuthorities;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return username;
        }

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

    }
}


