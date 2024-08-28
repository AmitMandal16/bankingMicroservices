package com.amit.accounts.mapper;

import com.amit.accounts.dto.CustomerDto;
import com.amit.accounts.entity.Customer;

public class CustomerMapper {
	
	 public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
	        customerDto.setName(customer.getName());
	        customerDto.setEmail(customer.getEmail());
	        customerDto.setMobileNumber(customer.getMobileNumber());
	        return customerDto;
	    }

	    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
	        customer.setName(customerDto.getName());
	        customer.setEmail(customerDto.getEmail());
	        customer.setMobileNumber(customerDto.getMobileNumber());
	        return customer;
	    }


}
