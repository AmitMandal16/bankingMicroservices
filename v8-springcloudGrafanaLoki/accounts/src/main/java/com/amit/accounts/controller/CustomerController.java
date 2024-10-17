package com.amit.accounts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amit.accounts.dto.CustomerDetailsDto;
import com.amit.accounts.dto.ErrorResponseDto;
import com.amit.accounts.service.ICustomersService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;

@Tag(
        name = "Rest API for customers in  AmitBank",
        description = "Rest API to fetch customer details in  AmitBank"
)
@RestController
@RequestMapping(path="/api")
@Validated
public class CustomerController {
	
	 private final ICustomersService iCustomersService;
	 
	 private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	    public CustomerController(ICustomersService iCustomersService){
	        this.iCustomersService = iCustomersService;
	    }

	    @Operation(
	            summary = "Fetch Customer Details REST API",
	            description = "REST API to fetch Customer details based on a mobile number"
	    )
	    @ApiResponses({
	            @ApiResponse(
	                    responseCode = "200",
	                    description = "HTTP Status OK"
	            ),
	            @ApiResponse(
	                    responseCode = "500",
	                    description = "HTTP Status Internal Server Error",
	                    content = @Content(
	                            schema = @Schema(implementation = ErrorResponseDto.class)
	                    )
	            )
	    }
	    )
	    @GetMapping("/fetchCustomerDetails")
	    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestHeader("amitbank-correlation-id")
	                                                                    String correlationId,
	    		                                                       @RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
	                                                                   String mobileNumber){
	        logger.debug("amitbank-correlation-id found : {}", correlationId);
	    	CustomerDetailsDto customerDetailsDto = iCustomersService.fetchCustomerDetails(mobileNumber, correlationId);
	        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);

	    }

}
