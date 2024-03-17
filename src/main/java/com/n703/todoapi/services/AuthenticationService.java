package com.n703.todoapi.services;

import com.n703.todoapi.dtos.request.SignUpRequestDTO;
import com.n703.todoapi.models.User;
import com.n703.todoapi.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

    public void create(SignUpRequestDTO dto) {
        var user = userRepository.findByEmail(dto.email());

        if (user != null) {
            throw new APIException("O email informado já existe no sistema");
        }

        var userModel = new User(dto);
        var hashedPassword = passwordEncoder.encode(dto.password());
        userModel.setPassword(hashedPassword);

        userRepository.save(userModel);
    }
}
