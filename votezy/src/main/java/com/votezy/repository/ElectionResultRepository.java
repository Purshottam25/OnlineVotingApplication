package com.votezy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.votezy.entity.ElectionResult;

public interface ElectionResultRepository extends JpaRepository<ElectionResult, Long>{
	
	Optional<ElectionResult> findByElectionName(String electionName);
}
