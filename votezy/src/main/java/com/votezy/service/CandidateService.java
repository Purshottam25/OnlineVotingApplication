package com.votezy.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.votezy.entity.Candidate;
import com.votezy.entity.Vote;
import com.votezy.exception.DuplicateResourceException;
import com.votezy.exception.ResourceNotFoundException;
import com.votezy.repository.CandidateRepository;

@Service
public class CandidateService {
	
	private CandidateRepository candidateRepository;
	
	@Autowired
	public CandidateService(CandidateRepository candidateRepository) {
		this.candidateRepository = candidateRepository;
	}
	
	public Candidate registerCandidate(Candidate candidate) {
		if(candidateRepository.existsByEmail(candidate.getEmail())) {
			throw new DuplicateResourceException("Candidate with EmailId "+candidate.getEmail()+" already exists");
		}
		return candidateRepository.save(candidate);
	}
	
	public List<Candidate> getAllCandidates(){
		return candidateRepository.findAll();
	}
	
	public Candidate getCandidateById(Long id) {
		Candidate candidate=candidateRepository.findById(id).orElse(null);
		if(candidate==null) {
			throw new ResourceNotFoundException("Candidate with "+id+ " not found");
		}
		return candidate;
	}
	
	public Candidate updateCandidate(Long id,Candidate updatedCandidate) {
		Candidate candidate=getCandidateById(id);
		if(updatedCandidate.getName()!=null) {
			candidate.setName(updatedCandidate.getName());
		}
		
		if(updatedCandidate.getEmail()!=null) {
			candidate.setEmail(updatedCandidate.getEmail());
		}
		
		if(updatedCandidate.getParty()!=null) {
			candidate.setParty(updatedCandidate.getParty());
		}
		
		return candidateRepository.save(candidate);
	}
	
	public Candidate deleteCandidateById(Long id) {
		Candidate candidate=getCandidateById(id);
		List<Vote> votes=candidate.getVote();
		for (Vote vote : votes) {
			vote.setCandidate(null);
		}
		candidate.getVote().clear();
		candidateRepository.delete(candidate);
		
		return candidate;
	}
	
}
