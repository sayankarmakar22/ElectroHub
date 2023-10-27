package com.sayan.ElectroHub.Controllers;

import com.sayan.ElectroHub.DTO.ProductRequest;
import com.sayan.ElectroHub.DTO.ProductUpdate;
import com.sayan.ElectroHub.Model.Product;
import com.sayan.ElectroHub.Response.MappingResponse;
import com.sayan.ElectroHub.Response.UniversalResponse;
import com.sayan.ElectroHub.Services.Implementation.ProductServicesImpl;
import com.sayan.ElectroHub.Services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("electroHub/product")
public class ProductControllers {
    @Autowired
    private ProductServicesImpl productServices;
    @Autowired
    private  UniversalResponse universalResponse;


    @PostMapping("/save")
    public ResponseEntity<UniversalResponse> saveProduct(@RequestBody ProductRequest product){
        List<Object> response = new ArrayList<>();
        try{
            response.add(productServices.saveProduct(product));
            universalResponse = MappingResponse.mapUniversalResponse("No Error",response);
            return new ResponseEntity<>(universalResponse, HttpStatus.CREATED);
        }
        catch (Exception e){
            response.add(null);
            universalResponse = MappingResponse.mapUniversalResponse("error",response);
            return new ResponseEntity<>(universalResponse, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<UniversalResponse> viewProduct(@PathVariable String id){
        List<Object> response = new ArrayList<>();
        try{
            response.add(productServices.viewProduct(id));
            universalResponse=MappingResponse.mapUniversalResponse("Okay",response);
            return new ResponseEntity<>(universalResponse,HttpStatus.FOUND);
        }
        catch (RuntimeException e){
            response.add("not found");
            universalResponse=MappingResponse.mapUniversalResponse("product id is not valid Please check it !",response);
            return new ResponseEntity<>(universalResponse,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UniversalResponse> deleteProduct(@PathVariable String id){
        List<Object> response = new ArrayList<>();
        try{
            response.add(productServices.deleteProduct(id));
            universalResponse = MappingResponse.mapUniversalResponse("No error",response);
            return new ResponseEntity<>(universalResponse, HttpStatus.OK);
        }
        catch (Exception e){
            response.add(null);
            universalResponse = MappingResponse.mapUniversalResponse("you have tried to delete an non-existing product",response);
            return new ResponseEntity<>(universalResponse, HttpStatus.CREATED);

        }
    }

    @PutMapping("/update")
    public ResponseEntity<UniversalResponse> updateProduct(@RequestBody ProductUpdate product){
        List<Object> response = new ArrayList<>();
        try{
            response.add(productServices.updateProduct(product));
            universalResponse = MappingResponse.mapUniversalResponse("No Error",response);
            return new ResponseEntity<>(universalResponse, HttpStatus.CREATED);
        }
        catch (Exception e){
            response.add(null);
            universalResponse = MappingResponse.mapUniversalResponse("you have tried to update an non-existing product",response);
            return new ResponseEntity<>(universalResponse, HttpStatus.CREATED);

        }
    }

    @GetMapping("/getAllProduct")
    public ResponseEntity<List<Product>> getAllProduct(){
        return new ResponseEntity<>(productServices.getAllProduct(),HttpStatus.FOUND);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Object>> getAllProduct(@PathVariable String keyword){
        return new ResponseEntity<>(productServices.searchProduct(keyword), HttpStatus.CREATED);
    }
    @GetMapping("/filter/{startPrice}/{endPrice}")
    public ResponseEntity<List<Object>> getAllProduct(@PathVariable long startPrice,@PathVariable long endPrice){
        return new ResponseEntity<>(productServices.filterProduct(startPrice,endPrice),HttpStatus.FOUND);
    }

}
