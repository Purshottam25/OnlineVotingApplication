package com.votezy.service;

import java.util.List;

import javax.management.relation.RelationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.votezy.entity.Candidate;
import com.votezy.entity.Vote;
import com.votezy.entity.Voter;
import com.votezy.exception.DuplicateResourceException;
import com.votezy.exception.ResourceNotFoundException;
import com.votezy.repository.CandidateRepository;
import com.votezy.repository.VoterRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
public class VoterService {
	private VoterRepository voterRepository;
	private CandidateRepository candidateRepository;
	
	@Autowired
	public VoterService(VoterRepository voterRepository, CandidateRepository candidateRepository) {
		this.voterRepository = voterRepository;
		this.candidateRepository = candidateRepository;
	}

	public Voter registerVoter(Voter voter) {
		
		if(voterRepository.existsByEmail(voter.getEmail())) {
			throw new DuplicateResourceException("Voter with EmailId "+voter.getEmail()+" already exists");
		}
		
		return voterRepository.save(voter);
	}
	
	public List<Voter> getAllVoters(){
		return voterRepository.findAll();
	}
	
	public Voter getVoterByid(Long id) {
		Voter voter=voterRepository.findById(id).orElse(null);
		if(voter==null) {
			throw new ResourceNotFoundException("Voter with id "+id+" not found");
		}
		
		return voter;
	}
	
	public Voter updateVoterByid(Long id , Voter updatedVoter) {
		Voter voter=voterRepository.findById(id).orElse(null);
		if(voter==null) {
			throw new ResourceNotFoundException("Voter with id "+id+" not found");
		}
		
		if(updatedVoter.getName()!=null) {
			voter.setName(updatedVoter.getName());
		}
		
		if(updatedVoter.getEmail()!=null) {
			voter.setEmail(updatedVoter.getEmail());
		}
		
		return voterRepository.save(voter);
	}
	
	@Transactional
	public Voter deleteVoterById(Long id) {
		Voter voter=voterRepository.findById(id).orElse(null);
		if(voter==null) {
			throw new ResourceNotFoundException("Cannot delete voter with id "+id+" since id is invalid");
		}
		
		Vote vote=voter.getVote();
		if(vote!=null) {
			vote.setVoter(null);
			Candidate candidate=vote.getCandidate();
			candidate.setVoteCount(candidate.getVoteCount()-1);
			candidateRepository.save(candidate);
		}
		
		voterRepository.delete(voter);
		return voter;
		
	}
	
}
