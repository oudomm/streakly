package dev.oudom.streakly.features.auth;

import dev.oudom.streakly.features.auth.dto.AuthResponse;
import dev.oudom.streakly.features.auth.dto.LoginRequest;
import dev.oudom.streakly.features.auth.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
