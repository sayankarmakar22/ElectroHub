package com.sayan.ElectroHub.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private String CustomerId;
    private String name;
    private String address;
    private String phoneNumber;
    private String password;
    private String registrationDateTime;
    private String email;
}
