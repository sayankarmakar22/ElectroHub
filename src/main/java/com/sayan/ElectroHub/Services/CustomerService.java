package com.sayan.ElectroHub.Services;

import com.sayan.ElectroHub.DTO.CustomerRequest;

import java.io.IOException;

public interface CustomerService {
    Object saveCust(CustomerRequest customerRequest) throws IOException;
    Object viewCust(String custId) throws Exception;
    Object updateCust(CustomerRequest customerRequest) throws IOException;
    String deleteCust(String custId) throws IOException;

    String checkCustomerId(String id);
}
