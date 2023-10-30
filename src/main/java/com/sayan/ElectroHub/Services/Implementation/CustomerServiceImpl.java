package com.sayan.ElectroHub.Services.Implementation;

import com.sayan.ElectroHub.DTO.CustomerRequest;
import com.sayan.ElectroHub.DTO.CustomerResponse;
import com.sayan.ElectroHub.Helper.CustomerHelper;
import com.sayan.ElectroHub.Model.Customer;
import com.sayan.ElectroHub.Repository.CustomerRepo;
import com.sayan.ElectroHub.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public Object saveCust(CustomerRequest customerRequest) {
        Customer savedCust = customerRepo.save(CustomerHelper.convertRequestToCustomer(customerRequest, new Customer()));
        return CustomerHelper.sentCustomerRequest(new CustomerResponse(), Optional.of(savedCust));
    }

    @Override
    public Object viewCust(String custId) {
        Optional<Customer> byId = customerRepo.findById(custId);
        return CustomerHelper.sentCustomerRequest(new CustomerResponse(),byId);
    }

    @Override
    public Object updateCust(CustomerRequest customerRequest) {
       return  CustomerHelper.sentCustomerRequest
                (new CustomerResponse(),
                    Optional.of(customerRepo.save
                          (CustomerHelper.convertRequestToCustomerUpdate
                              (customerRequest,customerRepo.findById
                                    (customerRequest.getCustomerId())))));
    }

    @Override
    public String deleteCust(String custId) {
        customerRepo.deleteById(custId);
        return "deleted id : " + custId;
    }

    @Override
    public String checkCustomerId(String id) {
         String result = customerRepo.existsById(id) ? "Customer Id Not Available" : "Customer Id Available";
         return result;
    }
}
