package com.sayan.ElectroHub.Redis.Model;

import jakarta.annotation.security.DenyAll;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("product")
public class RedisProduct implements Serializable {
    private String productId;
    private String productName;
    private long price;
    private Date launchDate;
    private String productDescription;
    private long quantity;

}
