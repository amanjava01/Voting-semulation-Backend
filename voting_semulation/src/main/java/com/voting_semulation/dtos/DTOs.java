package com.voting_semulation.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class DTOs {
	
	public record RegisterRequest(String name, String email, String password , String role) {}
	
	public record LoginRequest(String email,String password) {}
	
	public record AuthResponce(String token , Long userId,String name,String emial,String role) {}
	
//	public record ElectionRequest( String title, String description,  LocalDateTime startAt,  LocalDateTime endAt) {}
//    public record ElectionResponse(Long id, String title, String description, String status, LocalDateTime startAt, LocalDateTime endAt, boolean simulationMode) {}
//    
//    public record CandidateRequest( String name, String party) {}
//    public record CandidateResponse(Long id, Long electionId, String name, String party, String status) {}
//    
//    public record CastVoteRequest( Long candidateId, String idempotencyKey) {}
//    public record VoteReceiptResponse(String receiptCode, Long electionId, LocalDateTime castAt, String status) {}
//    public record VoterStatusResponse(boolean eligible, boolean hasVoted, LocalDateTime participatedAt) {}
//    
//    public record CandidateResult(Long candidateId, String name, String party, long voteCount, double voteShare) {}
//    public record ElectionResultResponse(Long electionId, String title, String status, long totalVotes, long eligibleVoters, double turnoutRate, List<CandidateResult> candidates) {}
//    
//    public record TimePoint(String timestamp, long votes, long cumulativeVotes) {}
//    public record AnalyticsOverview(long eligibleVoters, long totalBallots, double turnoutPct, double burstScore, long duplicateAttempts, List<CandidateResult> candidateStats, List<TimePoint> timeSeries) {}
//    public record SimulationRequest(int voterCount, String pattern, int duplicateAttempts) {}


}
