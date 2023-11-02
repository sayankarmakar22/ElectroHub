package com.sayan.ElectroHub.Controllers;

import com.sayan.ElectroHub.DTO.OrderRequest;
import com.sayan.ElectroHub.Services.Implementation.CartServicesImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/electroHub/cart")
public class CartControllers {
    @Autowired
    private CartServicesImpl cartServices;

    @PostMapping("/add/{id}/{pId}")
    public Object addToCart(@PathVariable String id,@PathVariable String pId){

        return new ResponseEntity<>(cartServices.addToCart(id,pId), HttpStatus.CREATED);
    }
}
