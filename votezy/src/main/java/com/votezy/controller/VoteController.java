package com.votezy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.votezy.dto.VoteRequestDTO;
import com.votezy.dto.VoteResponseDTO;
import com.votezy.entity.Vote;
import com.votezy.service.VoteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vote")
public class VoteController {
	private VoteService voteService;
	
	@Autowired
	public VoteController(VoteService voteService) {
		this.voteService = voteService;
	}
	
	@PostMapping("/cast")
	public ResponseEntity<VoteResponseDTO> castVote(@RequestBody @Valid VoteRequestDTO voteRequest){
		Vote vote=voteService.castVote(voteRequest.getVoterId(), voteRequest.getCandidateId());
		VoteResponseDTO voteresponse=new VoteResponseDTO("vote casted successfully",true,vote.getVoterId(),vote.getCandidateId());
		return new ResponseEntity<VoteResponseDTO>(voteresponse,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Vote>> getAllVotes(){
		List<Vote> voteList = voteService.getAllVotes();
		return new ResponseEntity<>(voteList,HttpStatus.OK);
	}
	
}
