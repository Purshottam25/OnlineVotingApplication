package com.votezy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.votezy.entity.Candidate;
import com.votezy.entity.Vote;
import com.votezy.entity.Voter;
import com.votezy.exception.ResourceNotFoundException;
import com.votezy.exception.VoteNotAllowedException;
import com.votezy.repository.CandidateRepository;
import com.votezy.repository.VoteRepository;
import com.votezy.repository.VoterRepository;

import jakarta.transaction.Transactional;

@Service
public class VoteService {
	
	private VoteRepository voteRepository;
	private CandidateRepository candidateRepository;
	private VoterRepository voterRepository;
	
	@Autowired
	public VoteService(VoteRepository voteRepository, CandidateRepository candidateRepository,
			VoterRepository voterRepository) {
		this.voteRepository = voteRepository;
		this.candidateRepository = candidateRepository;
		this.voterRepository = voterRepository;
	}
	
	
	@Transactional
	public Vote castVote(Long voterId, Long candidateId) {
		
		Voter voter=voterRepository.findById(voterId).orElse(null);
		if(voter==null) {
			throw new ResourceNotFoundException("Voter not found with id "+voterId);
		}
		
		Candidate candidate=candidateRepository.findById(candidateId).orElse(null);
		if(candidate==null) {
			throw new ResourceNotFoundException("candidate not found with id "+candidateId);
		}
		
		if(voter.isHasVoted()==true) {
			throw new VoteNotAllowedException(voterId+" has already casted vote");
		}
		
		Vote vote=new Vote();
		vote.setVoter(voter);
		vote.setCandidate(candidate);
				
		candidate.setVoteCount(candidate.getVoteCount()+1);
		candidateRepository.save(candidate);
		
		voter.setVote(vote);
		voter.setHasVoted(true);
		voterRepository.save(voter);
		
		return vote;
		
	}
	
	public List<Vote> getAllVotes(){
		return voteRepository.findAll();
	}
	
}
