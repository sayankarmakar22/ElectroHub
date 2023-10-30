package com.sayan.ElectroHub.Services.Implementation;

import com.sayan.ElectroHub.DTO.CustomerRequest;
import com.sayan.ElectroHub.DTO.CustomerResponse;
import com.sayan.ElectroHub.Helper.CustomerHelper;
import com.sayan.ElectroHub.Helper.LoggerHelper;
import com.sayan.ElectroHub.Model.Customer;
import com.sayan.ElectroHub.Redis.Helper.RedisCustomerHelper;
import com.sayan.ElectroHub.Redis.Model.RedisCustomer;
import com.sayan.ElectroHub.Repository.CustomerRepo;
import com.sayan.ElectroHub.Services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private RedisTemplate redisTemplate;

    private final static String hashKeyForCustSaving = "custSaving";

    private Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Override
    public Object saveCust(CustomerRequest customerRequest) throws IOException {
        Customer savedCust = customerRepo.save(CustomerHelper.convertRequestToCustomer(customerRequest, new Customer()));
        redisTemplate.opsForHash().put(
                        hashKeyForCustSaving,
                        customerRequest.getCustomerId(),
                        RedisCustomerHelper.convertToRedisProduct
                                (new RedisCustomer(),savedCust));
        LoggerHelper.writeLogToFile("saved customer to the redis");
        return CustomerHelper.sentCustomerRequest(new CustomerResponse(), Optional.of(savedCust));
    }

    @Override
    public Object viewCust(String custId) throws Exception {
        Object foundFromRedis = redisTemplate.opsForHash().get(hashKeyForCustSaving, custId);
        if(foundFromRedis != null || customerRepo.existsById(custId)){
            if(foundFromRedis == null){
                Optional<Customer> byId = customerRepo.findById(custId);
                LoggerHelper.writeLogToFile("customer found from sql");
                return CustomerHelper.sentCustomerRequest(new CustomerResponse(),byId);
            }
            else{
                LoggerHelper.writeLogToFile("customer found from redis");
                return foundFromRedis;
            }
        }
        throw new Exception();

    }

    @Override
    public Object updateCust(CustomerRequest customerRequest) throws IOException {
        if(!customerRepo.existsById(customerRequest.getCustomerId())){
            throw new RuntimeException();
        }
        Customer convertRequestToCustomerUpdate = CustomerHelper.convertRequestToCustomerUpdate(customerRequest, customerRepo.findById(customerRequest.getCustomerId()));
        redisTemplate.opsForHash().put(hashKeyForCustSaving,customerRequest.getCustomerId(),RedisCustomerHelper.convertToRedisProduct(new RedisCustomer(),convertRequestToCustomerUpdate));
        LoggerHelper.writeLogToFile("updated customer saved to redis \n");
        customerRepo.save(convertRequestToCustomerUpdate);
        LoggerHelper.writeLogToFile("updated customer saved to sql \n");
        return redisTemplate.opsForHash().get(hashKeyForCustSaving,customerRequest.getCustomerId());

    }

    @Override
    public String deleteCust(String custId) throws IOException {
        redisTemplate.opsForHash().delete(hashKeyForCustSaving,custId);
        customerRepo.deleteById(custId);
        LoggerHelper.writeLogToFile("delete customer from both redis and sql \n");
        return "deleted id : " + custId;
    }

    @Override
    public String checkCustomerId(String id) {
         String result = customerRepo.existsById(id) ? "Customer Id Not Available" : "Customer Id Available";
         return result;
    }
}
