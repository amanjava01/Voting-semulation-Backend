package com.voting_semulation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "vote_attempts", indexes = {
        @Index(name = "idx_attempt_outcome", columnList = "election_id, outcome, attemptedAt")
})
public class VoteAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long electionId;
    private Long voterId;
    private String idempotencyKey;
    private String outcome; // SUCCESS, ALREADY_VOTED, NOT_ELIGIBLE, ELECTION_NOT_OPEN
    private LocalDateTime attemptedAt = LocalDateTime.now();
}
