package com.sayan.ElectroHub.Redis.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("Admin")
public class RedisAdmin implements Serializable {
    private String adminId;
    private String name;
    private String password;
    private String email;
}
