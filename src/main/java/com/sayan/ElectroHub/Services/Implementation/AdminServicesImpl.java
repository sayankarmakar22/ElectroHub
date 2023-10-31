package com.sayan.ElectroHub.Services.Implementation;

import com.sayan.ElectroHub.DTO.CustomerResponse;
import com.sayan.ElectroHub.Helper.CustomerHelper;
import com.sayan.ElectroHub.Helper.LoggerHelper;
import com.sayan.ElectroHub.Model.Admin;
import com.sayan.ElectroHub.Model.Customer;
import com.sayan.ElectroHub.Redis.Helper.RedisAdminHelper;
import com.sayan.ElectroHub.Redis.Model.RedisAdmin;
import com.sayan.ElectroHub.Repository.AdminRepo;
import com.sayan.ElectroHub.Services.AdminServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServicesImpl implements AdminServices {

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private RedisTemplate redisTemplate;

    private Logger log = LoggerFactory.getLogger(AdminServicesImpl.class);

    private final String hashKeyForAdmin = "adminKey";
    @Override
    public Object saveAdmin(Admin admin) {
        redisTemplate.opsForHash().put(hashKeyForAdmin,admin.getAdminId(), RedisAdminHelper.convertToRedisAdmin(new RedisAdmin(),admin));
        return adminRepo.save(admin);
    }

    @Override
    public Object viewAdmin(String id) throws Exception {
        Object foundFromRedis = redisTemplate.opsForHash().get(hashKeyForAdmin, id);
        if(foundFromRedis != null || adminRepo.existsById(id)){
            if(foundFromRedis == null){
                log.info("customer found from sql");
                return adminRepo.findById(id);
            }
            else{
                log.info("customer found from redis");
                return foundFromRedis;
            }
        }
        throw new Exception();
    }

    @Override
    public Object updateAdmin(Admin admin) {
        if(!adminRepo.existsById(admin.getAdminId())){
            throw new RuntimeException();
        }
        redisTemplate.opsForHash().put(hashKeyForAdmin,admin.getAdminId(),RedisAdminHelper.convertToRedisAdmin(new RedisAdmin(),admin));
        return adminRepo.save(admin);
    }

    @Override
    public String deleteAdmin(String id) {
        if(!adminRepo.existsById(id)){
            throw new RuntimeException();
        }
        redisTemplate.opsForHash().delete(hashKeyForAdmin,id);
        adminRepo.deleteById(id);
        log.info(id + " has been successfully deleted from both redis and sql server");
        return "deleted id : " + id;
    }
}
