package com.sayan.ElectroHub.Redis.Helper;

import com.sayan.ElectroHub.Model.Product;
import com.sayan.ElectroHub.Redis.Model.*;

public class RedisProductHelper {

    public static RedisProduct convertToRedisProduct(Product product,RedisProduct redisProduct){
        redisProduct.setProductId(product.getProductId());
        redisProduct.setProductDescription(product.getProductDescription());
        redisProduct.setProductName(product.getProductName());
        redisProduct.setPrice(product.getPrice());
        redisProduct.setLaunchDate(product.getLaunchDate());
        redisProduct.setQuantity(product.getQuantity());
        return redisProduct;


    }
}
