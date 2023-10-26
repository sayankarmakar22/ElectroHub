package com.sayan.ElectroHub.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderList {
    @Id
    private String orderId;

    private long totalPrice;
    private String status;

    @OneToOne
    @JoinColumn(name = "cartId")
    private Cart cartId;

    @OneToOne
    @JoinColumn(name = "customerId")
    private Customer customerId;

}
