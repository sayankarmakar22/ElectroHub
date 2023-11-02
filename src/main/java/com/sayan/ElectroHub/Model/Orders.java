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
public class Orders {
    @Id
    private String orderId;
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private int id;


    private long totalPrice;
    private String status;
    private String orderDate;
    private String deliveryDate;

    @OneToOne
    @JoinColumn(name = "cartId")
    private Cart cartId;

    @OneToOne
    @JoinColumn(name = "customerId")
    private Customer customerId;

}
