package com.votezy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.votezy.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long>{
	
	boolean existsByEmail(String email);
	
	List<Candidate> findAllByOrderByVoteCountDesc();
}
