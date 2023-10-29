package com.sayan.ElectroHub.Controllers;

import com.sayan.ElectroHub.DTO.CustomerRequest;
import com.sayan.ElectroHub.Response.MappingResponse;
import com.sayan.ElectroHub.Response.UniversalResponse;
import com.sayan.ElectroHub.Services.Implementation.CustomerServiceImpl;
import org.hibernate.event.spi.ReplicateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/electroHub/customer")
public class CustomerControllers {

    @Autowired
    private CustomerServiceImpl customerService;



    private List<Object> response = new ArrayList<>();

    @PostMapping("/save")
    public ResponseEntity<UniversalResponse> saveCust(@RequestBody CustomerRequest customerRequest){
        try{
            response.clear();
            response.add(customerService.saveCust(customerRequest));
            return new ResponseEntity<>(MappingResponse.mapUniversalResponse("okay",response), HttpStatus.CREATED);
        }
        catch (Exception e){
            response.clear();
            response.add("null");
            e.printStackTrace();
            return new ResponseEntity<>(MappingResponse.mapUniversalResponse("Error while onboarding new customer",response), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{custId}")
    public ResponseEntity<UniversalResponse> viewCust(@PathVariable String custId){
        try{
            response.clear();
            response.add(customerService.viewCust(custId));
            return new ResponseEntity<>(MappingResponse.mapUniversalResponse("okay",response), HttpStatus.CREATED);
        }
        catch (Exception e){
            response.clear();
            response.add("No Data Found");
            e.printStackTrace();
            return new ResponseEntity<>(MappingResponse.mapUniversalResponse("Error while getting information about customer",response), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<UniversalResponse> updateCust(@RequestBody CustomerRequest customerRequest){
        try{
            response.clear();
            response.add(customerService.updateCust(customerRequest));
            return new ResponseEntity<>(MappingResponse.mapUniversalResponse("okay",response), HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            response.clear();
            response.add("oopss !!! ");
            e.printStackTrace();
            return new ResponseEntity<>(MappingResponse.mapUniversalResponse("Error while updating customer details",response), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{custId}")
    public ResponseEntity<UniversalResponse> deleteCust(@PathVariable String custId){
        try{
            response.clear();
            response.add(customerService.deleteCust(custId));
            return new ResponseEntity<>(MappingResponse.mapUniversalResponse("okay",response), HttpStatus.CREATED);
        }
        catch (Exception e){
            response.clear();
            response.add("nothing shown here");
            e.printStackTrace();
            return new ResponseEntity<>(MappingResponse.mapUniversalResponse("Error while deleting customer",response), HttpStatus.CREATED);
        }
    }
}
