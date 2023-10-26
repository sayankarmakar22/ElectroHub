package com.sayan.ElectroHub.Services.Implementation;

import com.sayan.ElectroHub.DTO.ProductRequest;
import com.sayan.ElectroHub.DTO.ProductUpdate;

import com.sayan.ElectroHub.Helper.ProductHelper;
import com.sayan.ElectroHub.Redis.Helper.RedisProductHelper;
import com.sayan.ElectroHub.Model.Product;
import com.sayan.ElectroHub.Redis.Model.RedisProduct;
import com.sayan.ElectroHub.Repository.ProductRepo;
import com.sayan.ElectroHub.Services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServicesImpl implements ProductServices {
    @Autowired
    private ProductRepo productRepo;

    private final static String hashKey = "product";

    @Autowired
    private RedisTemplate redisTemplate;


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
    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }

    @Override
    public List<Product> searchProduct(String keyword) {
        return productRepo.findByproductNameContaining(keyword);
    }

    @Override
    public List<Product> filterProduct(long startPrice, long endPrice) {
        return productRepo.findByPriceBetween(startPrice,endPrice);
    }
}
