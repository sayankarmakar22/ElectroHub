package com.sayan.ElectroHub.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String type;

    @ManyToOne
    @JoinColumn(name = "cartId")
    private Cart cartId;

    @OneToOne
    @JoinColumn(name = "productId")
    private Product productId;
}
