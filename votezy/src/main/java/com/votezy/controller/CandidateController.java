package com.votezy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.votezy.entity.Candidate;
import com.votezy.service.CandidateService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/candidate")
public class CandidateController {
	private CandidateService candidateService;
	
	@Autowired
	public CandidateController(CandidateService candidateService) {
		super();
		this.candidateService = candidateService;
	}
	
	@PostMapping("register")
	public ResponseEntity<Candidate> registerCandidate(@RequestBody @Valid Candidate candidate){
		Candidate savedCandidate=candidateService.registerCandidate(candidate);
		return new ResponseEntity<Candidate>(savedCandidate , HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Candidate>> getAllCandidates(){
		List<Candidate> candidateList=candidateService.getAllCandidates();
		return new ResponseEntity<List<Candidate>>(candidateList , HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Candidate> getCandidate(@PathVariable Long id){
		Candidate candidate=candidateService.getCandidateById(id);
		return new ResponseEntity<Candidate>(candidate , HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Candidate> updateCandidate(@PathVariable Long id , @RequestBody Candidate candidate){
		Candidate updatedCandidate=candidateService.updateCandidate(id, candidate);
		return new ResponseEntity<Candidate>(updatedCandidate , HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Candidate> deleteCandidate(@PathVariable Long id){
		Candidate candidate=candidateService.deleteCandidateById(id);
		return new ResponseEntity<Candidate>(candidate , HttpStatus.OK);
	}
}
