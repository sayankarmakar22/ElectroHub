package com.sayan.ElectroHub.Redis.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("Customer")
public class RedisCustomer implements Serializable {
    private String CustomerId;
    private String name;
    private String address;
    private String phoneNumber;
    private String password;
    private String registrationDateTime;
    private String email;
}
