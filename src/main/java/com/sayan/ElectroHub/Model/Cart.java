package com.sayan.ElectroHub.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cart {
    @Id
    private String cartId;

    @OneToOne
    @JoinColumn(name = "customerId")
    private Customer customerId;


}
