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
@Table(name = "ballots", indexes = {
        @Index(name = "idx_ballot_election_time", columnList = "election_id, castAt"),
        @Index(name = "idx_ballot_candidate", columnList = "election_id, candidate_id")
})
public class Ballot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "election_id", nullable = false)
    private Election election;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    @Column(unique = true, nullable = false, length = 80)
    private String receiptCode;

    @Column(nullable = false)
    private LocalDateTime castAt;

    @Column(nullable = false, length = 30)
    private String source = "USER"; // USER, SIMULATION
}
