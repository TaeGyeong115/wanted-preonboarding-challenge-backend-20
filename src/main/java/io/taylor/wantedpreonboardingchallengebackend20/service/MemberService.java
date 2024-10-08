package io.taylor.wantedpreonboardingchallengebackend20.service;

import io.taylor.wantedpreonboardingchallengebackend20.common.JwtTokenUtil;
import io.taylor.wantedpreonboardingchallengebackend20.common.PasswordUtil;
import io.taylor.wantedpreonboardingchallengebackend20.entity.Member;
import io.taylor.wantedpreonboardingchallengebackend20.model.request.JoinRequestDto;
import io.taylor.wantedpreonboardingchallengebackend20.model.request.LoginRequestDto;
import io.taylor.wantedpreonboardingchallengebackend20.model.response.JoinResponseDto;
import io.taylor.wantedpreonboardingchallengebackend20.model.response.LoginResponseDto;
import io.taylor.wantedpreonboardingchallengebackend20.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordUtil passwordUtil;
    private final JwtTokenUtil jwtTokenUtil;

    public MemberService(MemberRepository memberRepository, PasswordUtil passwordUtil, JwtTokenUtil jwtTokenUtil) {
        this.memberRepository = memberRepository;
        this.passwordUtil = passwordUtil;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public JoinResponseDto join(JoinRequestDto request) {
        request.setPassword(passwordUtil.encodePassword(request.getPassword()));
        Member member = memberRepository.save(new Member(request));
        return new JoinResponseDto(member);
    }

    public LoginResponseDto login(LoginRequestDto request) {
        Member member = memberRepository.findMemberByEmail(request.getEmail());
        if (member == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "회원 정보가 존재하지 않습니다.");
        if (!passwordUtil.matchPassword(request.getPassword(), member.getPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "올바르지 않은 비밀번호 입니다.");

        return new LoginResponseDto(member, jwtTokenUtil.generateToken(request.getEmail()));
    }

    public Member getMemberByEmail(String email) {
        return memberRepository.findMemberByEmail(email);
    }

    public Object logout(HttpHeaders headers, String str) {
        return true;
    }
}
