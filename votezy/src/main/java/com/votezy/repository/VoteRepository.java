package com.votezy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.votezy.entity.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long>{
	
}
