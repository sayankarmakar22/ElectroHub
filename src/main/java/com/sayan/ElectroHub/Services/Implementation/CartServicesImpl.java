package com.sayan.ElectroHub.Services.Implementation;

import com.sayan.ElectroHub.Model.Cart;
import com.sayan.ElectroHub.Model.CartItems;
import com.sayan.ElectroHub.Model.Customer;
import com.sayan.ElectroHub.Model.Product;
import com.sayan.ElectroHub.Repository.CartItemRepo;
import com.sayan.ElectroHub.Repository.CartRepo;
import com.sayan.ElectroHub.Repository.ProductRepo;
import com.sayan.ElectroHub.Services.CartServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartServicesImpl implements CartServices {
    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CartItemRepo cartItemRepo;
    private Logger log = LoggerFactory.getLogger(CartServicesImpl.class);
    @Override
    public Object addToCart(String id, String pId) {
        log.info(cartRepo.existsBycartId(id).toString());
       if(cartRepo.existsBycartId(id).toString().equals("false")){
           CartItems cartItems = new CartItems();
           Cart cart = new Cart();
           String cartId = cartRepo.findCartIdByCustomerId(id);
           cart.setCartId(cartId);
           cartItems.setCartId(cart);
           cartItems.setQuantity(1);
           cartItems.setProductId(pId);
           cartItemRepo.save(cartItems);
           return "cart Updated" + "cart number : " + cartId;
       }
       else{
           Cart cart = new Cart();
           CartItems cartItems = new CartItems();
           Customer customer = new Customer();

           cart.setCartId(String.valueOf(UUID.randomUUID()).substring(0,8));
           customer.setCustomerId(id);
           cart.setCustomerId(customer);
           cartRepo.save(cart);

           cartItems.setCartId(cart);
           cartItems.setQuantity(1);
           cartItems.setProductId(pId);
           cartItemRepo.save(cartItems);

           return "cart Created" + "cart number : " + cart.getCartId();

       }

    }
}
