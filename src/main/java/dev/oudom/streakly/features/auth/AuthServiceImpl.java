package dev.oudom.streakly.features.auth;

import dev.oudom.streakly.exception.AppException;
import dev.oudom.streakly.features.auth.dto.AuthResponse;
import dev.oudom.streakly.features.auth.dto.LoginRequest;
import dev.oudom.streakly.features.auth.dto.RegisterRequest;
import dev.oudom.streakly.features.user.User;
import dev.oudom.streakly.features.user.UserRepository;
import dev.oudom.streakly.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {

        // Check if email already exists
        if (userRepository.existsByEmail(request.email())) {
            throw new AppException("Email already exists", HttpStatus.CONFLICT);
        }

        // Build new user
        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password())); // hash password

        // Save to DB
        userRepository.save(user);

        // Generate token and return
        String token = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        // Verify email + password (throws exception if wrong)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        // Load user from DB
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        // Generate token and return
        String token = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }
}
