package com.sayan.ElectroHub.Services;

import com.sayan.ElectroHub.DTO.CustomerRequest;

public interface CustomerService {
    Object saveCust(CustomerRequest customerRequest);
    Object viewCust(String custId);
    Object updateCust(CustomerRequest customerRequest);
    String deleteCust(String custId);
}
