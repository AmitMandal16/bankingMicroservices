package com.cpmfcu.ivr.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cpmfcu.ivr.dto.RequestDTO;
import com.cpmfcu.ivr.service.FundTransferService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cpmfcu/v1")
@Validated
public class FundTransferController {
	
	private FundTransferService fundTransferService;
	
	public FundTransferController(FundTransferService fundTransferService) {
		this.fundTransferService = fundTransferService;
	}

	
	/**
	 * @author Amit Mandal
	 * @param requestDTO
	 * @return
	 * @throws IOException 
	 */
	@PostMapping("/sendTransferRequest")
	public ResponseEntity<String> fundTransferRequest(@Valid @RequestBody RequestDTO requestDTO) throws IOException{
		
		String sendRequest = fundTransferService.sendRequest(requestDTO);
		
		if(sendRequest!= null) {
			return ResponseEntity.status(HttpStatus.OK).body(sendRequest);
		}		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request could not be processed..");	
	}

}
