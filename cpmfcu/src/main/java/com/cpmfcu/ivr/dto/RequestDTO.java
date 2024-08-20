package com.cpmfcu.ivr.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RequestDTO {
	
     @NotEmpty(message = "PersonSerial can not be a null or empty")
	 private String personSerial;  
     
     @NotEmpty(message = "LoginSerial can not be a null or empty")
	 private String loginSerial;
     
     @NotEmpty(message = "TargetCategory can not be a null or empty")
	 private String targetCategory;
     
     @NotEmpty(message = "TargetSerial can not be a null or empty")
	 private String targetSerial;
     
     @NotEmpty(message = "Category can not be a null or empty")
	 private String category;
     
     @NotEmpty(message = "RegDOption can not be a null or empty")
	 private String regDOption;
     
     @NotEmpty(message = "TransferOption can not be a null or empty")
	 private String transferOption;
     
     @NotEmpty(message = "Amount can not be a null or empty")
	 private String amount;
     
     @NotEmpty(message = "RecipientSerial can not be a null or empty")
	 private String recipientSerial;
     
     @NotEmpty(message = "RecipientCategory can not be a null or empty")
	 private String recipientCategory;
     
     @NotEmpty(message = "Description can not be a null or empty")
	 private String description;

}
