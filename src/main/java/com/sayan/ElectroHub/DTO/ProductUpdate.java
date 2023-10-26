package com.sayan.ElectroHub.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdate {
    private String productId;
    private String productName;
    private long price;
    private String productDescription;
    private long quantity;
}
