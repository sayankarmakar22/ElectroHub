package com.sayan.ElectroHub.Helper;

import com.sayan.ElectroHub.DTO.ProductRequest;
import com.sayan.ElectroHub.DTO.ProductUpdate;
import com.sayan.ElectroHub.Model.Product;
import com.sayan.ElectroHub.Repository.ProductRepo;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class ProductHelper {

    public static Product convertSavedProductToProduct(ProductRequest savedProduct, Product product){
        product.setProductId(String.valueOf(UUID.randomUUID()).substring(0,7));
        product.setProductName(savedProduct.getProductName());
        product.setPrice(savedProduct.getPrice());
        product.setLaunchDate(new Date());
        product.setProductDescription(savedProduct.getProductDescription());
        product.setQuantity(savedProduct.getQuantity());
        return product;
    }

    public static Product convertSavedProductToProductUpdate(ProductUpdate savedProduct, Product product){
        product.setProductId(savedProduct.getProductId());
        product.setProductName(savedProduct.getProductName());
        product.setPrice(savedProduct.getPrice());
        product.setProductDescription(savedProduct.getProductDescription());
        product.setQuantity(savedProduct.getQuantity());
        return product;
    }
}
