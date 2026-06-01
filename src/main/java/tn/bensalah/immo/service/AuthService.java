package tn.bensalah.immo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tn.bensalah.immo.dto.LoginRequest;
import tn.bensalah.immo.dto.LoginResponse;
import tn.bensalah.immo.entity.User;
import tn.bensalah.immo.repository.UserRepository;
import tn.bensalah.immo.security.JwtUtils;
import tn.bensalah.immo.security.UserDetailsServiceImpl;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final UserDetailsServiceImpl userDetailsService;

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword()
            )
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtUtils.generateToken(userDetails.getUsername());

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        return new LoginResponse(
            token,
            user.getEmail(),
            user.getNom() + " " + user.getPrenom(),
            user.getRole().name()
        );
    }
}