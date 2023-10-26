package com.sayan.ElectroHub.Services;

import com.sayan.ElectroHub.DTO.ProductRequest;
import com.sayan.ElectroHub.DTO.ProductUpdate;
import com.sayan.ElectroHub.Model.Product;

public interface ProductServices {
    Product saveProduct(ProductRequest product) throws Exception;
    Product updateProduct(ProductUpdate product) throws Exception;
    Object viewProduct(String productId) ;
    String deleteProduct(String productId) throws Exception;
}
