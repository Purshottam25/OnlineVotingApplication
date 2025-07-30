package com.votezy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.votezy.dto.ElectionResultRequestDTO;
import com.votezy.dto.ElectionResultResponseDTO;
import com.votezy.entity.ElectionResult;
import com.votezy.service.ElectionResultService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/result")
public class ElectionResultController {
	
	private ElectionResultService electionResultService;
	
	@Autowired
	public ElectionResultController(ElectionResultService electionResultService) {
		this.electionResultService = electionResultService;
	}
	
	@PostMapping("/declare")
	public ResponseEntity<ElectionResultResponseDTO> declareElectionResult(@RequestBody @Valid ElectionResultRequestDTO electionResultDTO){
		ElectionResult electionResult=electionResultService.declareElectionResult(electionResultDTO.getElectionName());
		ElectionResultResponseDTO responseDTO=new ElectionResultResponseDTO();
		responseDTO.setElectionName(electionResult.getElectionName());
		responseDTO.setTotalVotes(electionResult.getTotalVotes());
		responseDTO.setWinnerId(electionResult.getWinnerId());
		responseDTO.setWinnerVotes(electionResult.getWinner().getVoteCount());
		return new ResponseEntity<ElectionResultResponseDTO>(responseDTO,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<ElectionResult>> getAllResults(){
		List<ElectionResult> result=electionResultService.getAllElectionResult();
		return new ResponseEntity<List<ElectionResult>>(result,HttpStatus.OK);
	}
	
}
