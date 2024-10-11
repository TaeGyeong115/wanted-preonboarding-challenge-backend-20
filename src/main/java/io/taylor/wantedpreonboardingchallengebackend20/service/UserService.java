package io.taylor.wantedpreonboardingchallengebackend20.service;

import io.taylor.wantedpreonboardingchallengebackend20.util.JwtTokenUtil;
import io.taylor.wantedpreonboardingchallengebackend20.util.PasswordUtil;
import io.taylor.wantedpreonboardingchallengebackend20.entity.User;
import io.taylor.wantedpreonboardingchallengebackend20.dto.request.JoinRequestDto;
import io.taylor.wantedpreonboardingchallengebackend20.dto.request.LoginRequestDto;
import io.taylor.wantedpreonboardingchallengebackend20.dto.response.JoinResponseDto;
import io.taylor.wantedpreonboardingchallengebackend20.dto.response.LoginResponseDto;
import io.taylor.wantedpreonboardingchallengebackend20.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordUtil passwordUtil;
    private final JwtTokenUtil jwtTokenUtil;

    public UserService(UserRepository userRepository, PasswordUtil passwordUtil, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.passwordUtil = passwordUtil;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public JoinResponseDto join(JoinRequestDto request) {
        request.setPassword(passwordUtil.encodePassword(request.getPassword()));
        User user = userRepository.save(new User(request));
        return new JoinResponseDto(user);
    }

    public LoginResponseDto login(LoginRequestDto request) {
        User user = userRepository.findUserByEmail(request.getEmail());
        if (user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "회원 정보가 존재하지 않습니다.");
        if (!passwordUtil.matchPassword(request.getPassword(), user.getPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "올바르지 않은 비밀번호 입니다.");

        return new LoginResponseDto(user, jwtTokenUtil.generateToken(request.getEmail()));
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public Object logout(HttpHeaders headers, String str) {
        return true;
    }
}
