package com.sayan.ElectroHub.Model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
    @Id
    private String CustomerName;

    private String name;
    @Column(length = 500)
    private String address;

    private String phoneNumber;

    @Column(length = 1500)
    private String password;

    private Date registrationDateTime;

    private String email;
}
