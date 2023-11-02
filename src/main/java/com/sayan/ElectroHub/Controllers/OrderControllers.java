package com.sayan.ElectroHub.Controllers;

import com.sayan.ElectroHub.DTO.OrderRequest;
import com.sayan.ElectroHub.Model.Cart;
import com.sayan.ElectroHub.Services.Implementation.OrderServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/electroHub/order")
public class OrderControllers {
    @Autowired
    private OrderServicesImpl orderServices;
    @PostMapping("/placed")
    public ResponseEntity<Object> placedOrder(@RequestBody OrderRequest orderRequest){
        return new ResponseEntity<>(orderServices.placedOrder(orderRequest), HttpStatus.CREATED);
    }
}
