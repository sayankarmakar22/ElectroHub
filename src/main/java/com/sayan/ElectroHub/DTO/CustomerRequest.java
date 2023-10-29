package com.sayan.ElectroHub.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CustomerRequest {
    private String customerId;
    private String name;
    private String address;
    private String phoneNumber;
    private String password;
    private String email;

}
