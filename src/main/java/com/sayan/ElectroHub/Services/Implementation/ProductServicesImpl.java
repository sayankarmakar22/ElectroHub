package com.sayan.ElectroHub.Services.Implementation;

import com.sayan.ElectroHub.DTO.ProductRequest;
import com.sayan.ElectroHub.DTO.ProductUpdate;

import com.sayan.ElectroHub.Helper.ProductHelper;
import com.sayan.ElectroHub.Redis.Helper.RedisProductHelper;
import com.sayan.ElectroHub.Model.Product;
import com.sayan.ElectroHub.Redis.Model.RedisProduct;
import com.sayan.ElectroHub.Repository.ProductRepo;
import com.sayan.ElectroHub.Services.ProductServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServicesImpl implements ProductServices {
    @Autowired
    private ProductRepo productRepo;

    private final static String hashKey = "product";

    @Autowired
    private RedisTemplate redisTemplate;

    private int requestTime = 0;

    private Logger log = LoggerFactory.getLogger(ProductServicesImpl.class);


    @Override
    public Product saveProduct(ProductRequest product) throws Exception {
        Product savedProduct = ProductHelper.convertSavedProductToProduct(product,new Product());
        redisTemplate.opsForHash().put(hashKey,savedProduct.getProductId(), RedisProductHelper.convertToRedisProduct(savedProduct,new RedisProduct()));
        return productRepo.save(savedProduct);
    }

    @Override
    public Product updateProduct(ProductUpdate product) throws Exception {
        Product savedProduct = ProductHelper.convertSavedProductToProductUpdate(product, productRepo.findByproductId(product.getProductId()));
        redisTemplate.opsForHash().put(hashKey,savedProduct.getProductId(),RedisProductHelper.convertToRedisProduct(savedProduct,new RedisProduct()));
        if(productRepo.existsByproductId(product.getProductId())){
            productRepo.save(savedProduct);
            return productRepo.findByproductId(product.getProductId());
        }
        throw new RuntimeException("error while updating product");
    }

    @Override
    public Object viewProduct(String productId)  {
        Object foundFromRedisServer = redisTemplate.opsForHash().get(hashKey, productId);
        if(foundFromRedisServer != null || productRepo.existsByproductId(productId)){
            if(foundFromRedisServer != null){
                return foundFromRedisServer;
            }
            else {
                return productRepo.findByproductId(productId);
            }
        }
        throw new RuntimeException("product not found");
    }

    @Override
    public String deleteProduct(String productId) throws Exception {
        redisTemplate.opsForHash().delete(hashKey,productId);
        if (productRepo.existsByproductId(productId)) {
            productRepo.deleteById(productId);
            return "product has been deleted and the id is : " + productId;
        }
        throw new RuntimeException("error while deleting product");


    }

    @Override
    public List<Object> getAllProduct() {
        List<Product> all = productRepo.findAll();
        if(requestTime == 0){
            requestTime = 1;
            log.info("requestTime = " + requestTime);
            return Collections.singletonList(all);
        }
        else if(requestTime == 1){
            log.info("requestTime = " +requestTime);
                redisTemplate.opsForHash().put(hashKey,"allProducts",all);
            return (List<Object>) redisTemplate.opsForHash().get(hashKey,"allProducts");
        }
        return (List<Object>) redisTemplate.opsForHash().get(hashKey,"allProducts");
    }
    @Scheduled(fixedRate = 30000)
    private void DeleteAllSavedProductRedis(){
        Set keys = redisTemplate.opsForHash().keys(hashKey);
        redisTemplate.opsForHash().delete(hashKey,keys);
        log.info("cleared");
    }



    @Override
    public List<Object> searchProduct(String keyword) {
        Object foundFromRedisSearchedProduct = redisTemplate.opsForHash().get(hashKey, keyword);
        if(foundFromRedisSearchedProduct == null){
            List<Product> searchedProduct = productRepo.findByproductNameContaining(keyword);
            redisTemplate.opsForHash(). put(hashKey,keyword,searchedProduct);
            return Arrays.asList(searchedProduct.toArray());
        }
        return (List<Object>) foundFromRedisSearchedProduct;
    }

    @Override
    public List<Object> filterProduct(long startPrice, long endPrice) {
        String keyForRedis = "filter " + String.valueOf(startPrice) + String.valueOf(endPrice);
        Object foundFromRedisFilterProduct = redisTemplate.opsForHash().get(hashKey, keyForRedis);
        if(foundFromRedisFilterProduct == null){
            List<Product> filteredProduct = productRepo.findByPriceBetween(startPrice, endPrice);
            redisTemplate.opsForHash(). put(hashKey,keyForRedis,filteredProduct);
            return Arrays.asList(filteredProduct.toArray());
        }
        return (List<Object>) foundFromRedisFilterProduct;
    }
}
