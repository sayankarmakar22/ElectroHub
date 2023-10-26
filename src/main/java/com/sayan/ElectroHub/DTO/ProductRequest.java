package com.sayan.ElectroHub.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String productName;
    private long price;
    private String productDescription;
    private long quantity;
}
