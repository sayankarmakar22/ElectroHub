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

    private final static String hashKeyForSearched = "searchedProduct";
    private final static String hashKeyForSaving = "productSaving";
    private final static String hashKeyForFilter = "productFilter";
    private final static String hashKeyForAll = "productAll";


    @Autowired
    private RedisTemplate redisTemplate;

    private int requestTime = 0;

    private Logger log = LoggerFactory.getLogger(ProductServicesImpl.class);


    @Override
    public Product saveProduct(ProductRequest product) throws Exception {
        Product savedProduct = ProductHelper.convertSavedProductToProduct(product,new Product());
        redisTemplate.opsForHash().put(hashKeyForSaving,savedProduct.getProductId(), RedisProductHelper.convertToRedisProduct(savedProduct,new RedisProduct()));
        redisTemplate.opsForHash().put(hashKeyForAll,savedProduct.getProductId(), RedisProductHelper.convertToRedisProduct(savedProduct,new RedisProduct()));
        log.info("saved product to the redis");
        return productRepo.save(savedProduct);
    }

    @Override
    public Product updateProduct(ProductUpdate product) throws Exception {
        Product savedProduct = ProductHelper.convertSavedProductToProductUpdate(product, productRepo.findByproductId(product.getProductId()));
        redisTemplate.opsForHash().put(hashKeyForSaving,savedProduct.getProductId(),RedisProductHelper.convertToRedisProduct(savedProduct,new RedisProduct()));
        log.info("updated product id " + product.getProductId() + " to the redis server");
        if(productRepo.existsByproductId(product.getProductId())){
            productRepo.save(savedProduct);
            log.info("updated product id " + product.getProductId() + " to the sql server");
            return productRepo.findByproductId(product.getProductId());
        }
        throw new RuntimeException("error while updating product");
    }

    @Override
    public Object viewProduct(String productId)  {
        Object foundFromRedisServer = redisTemplate.opsForHash().get(hashKeyForSaving, productId);
        if(foundFromRedisServer != null || productRepo.existsByproductId(productId)){
            if(foundFromRedisServer != null){
                log.info("found from redis");
                return foundFromRedisServer;
            }
            else {
                log.info("found from sql");
                return productRepo.findByproductId(productId);
            }
        }
        throw new RuntimeException("product not found");
    }

    @Override
    public String deleteProduct(String productId) throws Exception {
        redisTemplate.opsForHash().delete(hashKeyForSaving,productId);
        if (productRepo.existsByproductId(productId)) {
            productRepo.deleteById(productId);
            return "product has been deleted and the id is : " + productId;
        }
        throw new RuntimeException("error while deleting product");


    }

    @Override
    public List<Product> getAllProduct() {
        log.info("found from redis - get all product");
       return redisTemplate.opsForHash().values(hashKeyForAll);
    }



    @Override
    public List<Object> searchProduct(String keyword) {
        Object foundFromRedisSearchedProduct = redisTemplate.opsForHash().get(hashKeyForSearched, keyword);
        if(foundFromRedisSearchedProduct == null){
            log.info("searched keyword and their data are not present in the redis server so go db calls");
            List<Product> searchedProduct = productRepo.findByproductNameContaining(keyword);

            redisTemplate.opsForHash(). put(hashKeyForSearched,keyword,searchedProduct);
            return Arrays.asList(searchedProduct.toArray());
        }
        log.info("searched keyword and their data are present in the redis server so not to make db calls");
        return (List<Object>) foundFromRedisSearchedProduct;
    }

    @Override
    public List<Object> filterProduct(long startPrice, long endPrice) {
        String keyForRedis = "filter " + String.valueOf(startPrice) + String.valueOf(endPrice);
        Object foundFromRedisFilterProduct = redisTemplate.opsForHash().get(hashKeyForFilter, keyForRedis);
        if(foundFromRedisFilterProduct == null){
            log.info("searched price and their data are not present in the redis server so go db calls");
            List<Product> filteredProduct = productRepo.findByPriceBetween(startPrice, endPrice);
            redisTemplate.opsForHash(). put(hashKeyForFilter,keyForRedis,filteredProduct);
            return Arrays.asList(filteredProduct.toArray());
        }
        log.info("searched price and their data are present in the redis server so not make db calls");
        return (List<Object>) foundFromRedisFilterProduct;
    }
}
