package com.votezy.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteRequestDTO {
	
	@NotNull(message = "Voter Id is required")
	private Long voterId;
	
	@NotNull(message = "Candidate Id is required")
	private Long candidateId;
}
