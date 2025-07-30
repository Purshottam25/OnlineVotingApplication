package com.votezy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.votezy.entity.Candidate;
import com.votezy.entity.ElectionResult;
import com.votezy.exception.ResourceNotFoundException;
import com.votezy.repository.CandidateRepository;
import com.votezy.repository.ElectionResultRepository;
import com.votezy.repository.VoteRepository;

@Service
public class ElectionResultService {
	
	private ElectionResultRepository electionResultRepository;
	private VoteRepository voteRepository;
	private CandidateRepository candidateRepository;
	
	@Autowired
	public ElectionResultService(ElectionResultRepository electionResultRepository, VoteRepository voteRepository,
			CandidateRepository candidateRepository) {
		this.electionResultRepository = electionResultRepository;
		this.voteRepository = voteRepository;
		this.candidateRepository = candidateRepository;
	}
	
	public ElectionResult declareElectionResult(String electionName) {
		Optional<ElectionResult> existingResult=electionResultRepository.findByElectionName(electionName);
		if(existingResult.isPresent()) {
			return existingResult.get();
		}
		
		if(voteRepository.count()==0) {
			throw new IllegalStateException("Cannot declare result as no votes have been casted");
		}
		
		List<Candidate> allCandidates = candidateRepository.findAllByOrderByVoteCountDesc();
		if(allCandidates.isEmpty()) {
			throw new ResourceNotFoundException("No candidates available");
		}
		
		Candidate winner=allCandidates.get(0);
		
		int totalVotes=0;
		for (Candidate candidate : allCandidates) {
			totalVotes+=candidate.getVoteCount();
		}
		
		ElectionResult result=new ElectionResult();
		result.setElectionName(electionName);
		result.setTotalVotes(totalVotes);
		result.setWinner(winner);
		
		electionResultRepository.save(result);
		return result;
	}
	
	public List<ElectionResult> getAllElectionResult(){
		return electionResultRepository.findAll();
	}
	
}
