package com.sayan.ElectroHub.Redis.Helper;

import com.sayan.ElectroHub.Model.Admin;
import com.sayan.ElectroHub.Redis.Model.RedisAdmin;

public class RedisAdminHelper {
    public static RedisAdmin convertToRedisAdmin(RedisAdmin redisAdmin, Admin admin){
        redisAdmin.setAdminId(admin.getAdminId());
        redisAdmin.setName(admin.getName());
        redisAdmin.setPassword(admin.getPassword());
        redisAdmin.setEmail(admin.getEmail());
        return redisAdmin;
    }

}
