package com.sayan.ElectroHub.Services.Implementation;

import com.sayan.ElectroHub.DTO.OrderRequest;
import com.sayan.ElectroHub.Model.Cart;
import com.sayan.ElectroHub.Model.Customer;
import com.sayan.ElectroHub.Model.Orders;
import com.sayan.ElectroHub.Model.Product;
import com.sayan.ElectroHub.Repository.*;
import com.sayan.ElectroHub.Services.OrderServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderServicesImpl implements OrderServices {
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private CartItemRepo cartItemRepo;
    @Autowired
    private OrdersRepo ordersRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CustomerRepo customerRepo;

    private Logger log = LoggerFactory.getLogger(OrderServicesImpl.class);
    @Override
    public Object placedOrder(OrderRequest orderRequest) {
        if(!(customerRepo.existsById(orderRequest.getCustomerId()) && cartRepo.existsBycartId(orderRequest.getCartId()))) {
            throw new RuntimeException();
        }
            List<String> productIdByCartId = cartItemRepo.findProductIdByCartId(orderRequest.getCartId());
        log.info("products got by cart id " + productIdByCartId);
            List<Long> allById = productRepo.findAllProductPriceById(productIdByCartId);
        log.info("products price got by cart id " + allById);
            double totalPrice = 0;

            log.info("total value : " + totalPrice);
            for(int i = 0;i< allById.size();i++){
                log.info("amount : " + allById.get(i));
                totalPrice = totalPrice + allById.get(i);
                log.info("total value : " + totalPrice);

            }
            Orders orders = new Orders();
            orders.setOrderId(String.valueOf(UUID.randomUUID()).substring(0,10));
            orders.setTotalPrice((long) totalPrice);
            orders.setStatus("ORDER PLACED");
            orders.setOrderDate(String.valueOf(new Date()).substring(0,11));
            orders.setDeliveryDate("after 5 days");

            Cart cartIdSavedToOrder = new Cart();
            cartIdSavedToOrder.setCartId(orderRequest.getCartId());

            orders.setCartId(cartIdSavedToOrder);

            Customer customerSavedToOrder = new Customer();
            customerSavedToOrder.setCustomerId(orderRequest.getCustomerId());

            orders.setCustomerId(customerSavedToOrder);
            ordersRepo.save(orders);
            return "order placed";

    }

    @Override
    public Object viewOrder(String orderId) {
        return null;
    }
}
