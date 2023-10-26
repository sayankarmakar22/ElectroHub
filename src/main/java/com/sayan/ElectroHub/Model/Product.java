package com.sayan.ElectroHub.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    private String productId;

    @Column(length = 100)
    private String productName;


    private long price;

    private Date launchDate;

    @Column(length = 300)
    private String productDescription;

    private long quantity;


}
