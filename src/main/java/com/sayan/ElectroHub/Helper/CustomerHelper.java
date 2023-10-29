package com.sayan.ElectroHub.Helper;

import com.sayan.ElectroHub.DTO.CustomerRequest;
import com.sayan.ElectroHub.DTO.CustomerResponse;
import com.sayan.ElectroHub.Model.Customer;

import java.util.Date;
import java.util.Optional;

public class CustomerHelper {

    public static Customer convertRequestToCustomer(CustomerRequest customerRequest, Customer customer){
        customer.setCustomerId(customerRequest.getCustomerId());
        customer.setName(customerRequest.getName());
        customer.setAddress(customerRequest.getAddress());
        customer.setPhoneNumber(customerRequest.getPhoneNumber());
        customer.setPassword(customerRequest.getPassword());
        customer.setRegistrationDateTime(new Date());
        customer.setEmail(customerRequest.getEmail());
        return customer;
    }

    public static Customer convertRequestToCustomerUpdate(CustomerRequest customerRequest, Optional<Customer> customer){
         customer.orElse(null).setCustomerId(customerRequest.getCustomerId());
         customer.orElse(null).setName(customerRequest.getName());
         customer.orElse(null).setAddress(customerRequest.getAddress());
         customer.orElse(null).setPhoneNumber(customerRequest.getPhoneNumber());
         customer.orElse(null).setPassword(customerRequest.getPassword());
         customer.orElse(null).setRegistrationDateTime( customer.orElse(null).getRegistrationDateTime());
         customer.orElse(null).setEmail(customerRequest.getEmail());
        return customer.orElse(null);
    }
    public static CustomerResponse sentCustomerRequest(CustomerResponse customerResponse, Optional<Customer> customer){
        customerResponse.setCustomerId(customer.orElse(null).getCustomerId());
        customerResponse.setName(customer.orElse(null).getName());
        customerResponse.setAddress(customer.orElse(null).getAddress());
        customerResponse.setPhoneNumber(customer.orElse(null).getPhoneNumber());
        customerResponse.setRegistrationDateTime(customer.orElse(null).getRegistrationDateTime().toString().substring(0,10));
        customerResponse.setPassword(customer.orElse(null).getPassword());
        customerResponse.setEmail(customer.orElse(null).getEmail());
        return customerResponse;
    }
}
