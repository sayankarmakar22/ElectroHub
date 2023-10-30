package com.sayan.ElectroHub.Redis.Helper;

import com.sayan.ElectroHub.Model.Customer;
import com.sayan.ElectroHub.Redis.Model.RedisCustomer;

public class RedisCustomerHelper {

    public static RedisCustomer convertToRedisProduct(RedisCustomer redisCustomer, Customer customer){
        redisCustomer.setCustomerId(customer.getCustomerId());
        redisCustomer.setName(customer.getName());
        redisCustomer.setAddress(customer.getAddress());
        redisCustomer.setPhoneNumber(customer.getPhoneNumber());
        redisCustomer.setPassword(customer.getPassword());
        redisCustomer.setRegistrationDateTime(customer.getRegistrationDateTime().toString().substring(0,10));
        redisCustomer.setEmail(customer.getEmail());
        return redisCustomer;
    }
}
