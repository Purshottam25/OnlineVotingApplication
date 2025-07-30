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

import com.votezy.entity.Voter;
import com.votezy.service.VoterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/voter")
public class VoterController {
	
	private VoterService voterService;
	
	@Autowired
	public VoterController(VoterService voterService) {
		super();
		this.voterService = voterService;
	}

	@PostMapping("/register")
	public ResponseEntity<Voter> registerVoter(@RequestBody @Valid Voter voter){
		Voter registeredVoter = voterService.registerVoter(voter);
		return new ResponseEntity<Voter>(registeredVoter , HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Voter> getVoterById(@PathVariable("id") Long id){
		Voter voter=voterService.getVoterByid(id);
		return new ResponseEntity<Voter>(voter , HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Voter>> getAllVoters(){
		List<Voter> voterList = voterService.getAllVoters();
		return new ResponseEntity<List<Voter>>(voterList , HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Voter> updateVoter(@PathVariable Long id , @RequestBody Voter voter){
		Voter updatedVoter=voterService.updateVoterByid(id, voter);
		return new ResponseEntity<Voter>(updatedVoter , HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Voter> deleteVoterById(@PathVariable Long id){
		Voter voter=voterService.deleteVoterById(id);
		return new ResponseEntity<Voter>(voter , HttpStatus.OK);
	}
}
