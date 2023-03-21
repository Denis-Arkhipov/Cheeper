package com.denar.cheeper.services;

import com.denar.cheeper.datalayer.entities.Role;
import com.denar.cheeper.datalayer.entities.User;
import com.denar.cheeper.datalayer.repositories.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Data
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw  new UsernameNotFoundException("User not found");
        }

        user.setEnabled(true);
        userRepository.save(user);
        return user;
    }

    public List<User> readAllExceptMe(String username) {
        return userRepository.findAllExceptMe(username);
    }

    public User readByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void save(String username, String password) {
        User newUser = User.builder()
                .username(username)
                .password(new BCryptPasswordEncoder().encode(password))
                .authorities(Collections.singleton(Role.USER))
                .accountNonLocked(true)
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
        userRepository.save(newUser);
    }
}
