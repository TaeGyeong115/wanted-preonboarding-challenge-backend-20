package io.taylor.wantedpreonboardingchallengebackend20.model.response;

import java.sql.Timestamp;

public record ProductResponse(Long id, String name, long price, int status, Timestamp updatedAt, Timestamp createdAt) {
}
