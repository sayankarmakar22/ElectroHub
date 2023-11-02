package com.sayan.ElectroHub.Repository;

import com.sayan.ElectroHub.Model.Cart;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CartRepo extends JpaRepository<Cart,Integer> {
    Boolean existsBycartId(String cartId);
    @Query(value = "select cart_id from cart where customer_id =:id",nativeQuery = true)
    String findCartIdByCustomerId(String id);

    @Query(value = "select count(*) from cart where customer_id =:id",nativeQuery = true)
    Map<String,Object> existsBycustomerId(String id);
}
