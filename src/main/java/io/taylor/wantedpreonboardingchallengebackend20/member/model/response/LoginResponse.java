package io.taylor.wantedpreonboardingchallengebackend20.member.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import io.taylor.wantedpreonboardingchallengebackend20.member.entity.Member;

@Data
@NoArgsConstructor
public class LoginResponse {
    private String name;
    private String nickName;
    private String accessToken;

    public LoginResponse(Member member, String accessToken) {
        this.name = member.getName();
        this.nickName = member.getNickName();
        this.accessToken = accessToken;
    }
}
