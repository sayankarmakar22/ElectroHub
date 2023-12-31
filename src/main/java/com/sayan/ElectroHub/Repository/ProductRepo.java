package com.sayan.ElectroHub.Repository;

import com.sayan.ElectroHub.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,String> {
    Product findByproductId(String productId);
    Boolean existsByproductId(String productId);

    List<Product> findByproductNameContaining(String keyword);
    List<Product> findByPriceBetween(long minPrice, long maxPrice);


    @Query(value = "SELECT price FROM product where product_id in :ids",nativeQuery = true)
    List<Long> findAllProductPriceById(List<String> ids);

}
