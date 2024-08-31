package com.amit.accounts.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.amit.accounts.dto.AccountsDto;
import com.amit.accounts.dto.CardsDto;
import com.amit.accounts.dto.CustomerDetailsDto;
import com.amit.accounts.dto.LoansDto;
import com.amit.accounts.entity.Accounts;
import com.amit.accounts.entity.Customer;
import com.amit.accounts.exception.ResourceNotFoundException;
import com.amit.accounts.mapper.AccountsMapper;
import com.amit.accounts.mapper.CustomerMapper;
import com.amit.accounts.repository.AccountsRepository;
import com.amit.accounts.repository.CustomerRepository;
import com.amit.accounts.service.client.CardsFeignClient;
import com.amit.accounts.service.client.LoansFeignClient;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;

    }
}
