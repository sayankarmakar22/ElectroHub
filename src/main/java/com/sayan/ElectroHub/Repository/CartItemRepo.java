package com.sayan.ElectroHub.Repository;

import com.sayan.ElectroHub.Model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepo extends JpaRepository<CartItems,Integer> {
   @Query(value = "SELECT product_id FROM cart_items where cart_Id=:cartId",nativeQuery = true)
   List<String> findProductIdByCartId(String cartId);
}
