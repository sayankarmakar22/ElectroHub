package com.sayan.ElectroHub.Controllers;

import com.sayan.ElectroHub.DTO.CustomerRequest;
import com.sayan.ElectroHub.Model.Admin;
import com.sayan.ElectroHub.Response.MappingResponse;
import com.sayan.ElectroHub.Response.UniversalResponse;
import com.sayan.ElectroHub.Services.Implementation.AdminServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/electroHub/admin")
public class AdminControllers {

    @Autowired
    private AdminServicesImpl adminServices;

    private List<Object> response = new ArrayList<>();

    @PostMapping("/save")
    public ResponseEntity<UniversalResponse> saveAdmin(@RequestBody Admin admin){
        try{
            response.clear();
            response.add(adminServices.saveAdmin(admin));
            return new ResponseEntity<>(MappingResponse.mapUniversalResponse("okay",response), HttpStatus.CREATED);
        }
        catch (Exception e){
            response.clear();
            response.add("null");
            e.printStackTrace();
            return new ResponseEntity<>(MappingResponse.mapUniversalResponse("Error while onboarding new admin",response), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<UniversalResponse> viewAdmin(@PathVariable String id){
        try{
            response.clear();
            response.add(adminServices.viewAdmin(id));
            return new ResponseEntity<>(MappingResponse.mapUniversalResponse("okay",response), HttpStatus.CREATED);
        }
        catch (Exception e){
            response.clear();
            response.add("null");
            e.printStackTrace();
            return new ResponseEntity<>(MappingResponse.mapUniversalResponse("Error while getting information about admin",response), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<UniversalResponse> updateAdmin(@RequestBody Admin admin) {
        try {
            response.clear();
            response.add(adminServices.updateAdmin(admin));
            return new ResponseEntity<>(MappingResponse.mapUniversalResponse("okay", response), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response.clear();
            response.add("oopss !!! ");
            e.printStackTrace();
            return new ResponseEntity<>(MappingResponse.mapUniversalResponse("Error while updating admin details", response), HttpStatus.NOT_ACCEPTABLE);

        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<UniversalResponse> deleteAdmin(@PathVariable String id){
        try{
            response.clear();
            response.add(adminServices.deleteAdmin(id));
            return new ResponseEntity<>(MappingResponse.mapUniversalResponse("okay",response), HttpStatus.CREATED);
        }
        catch (Exception e){
            response.clear();
            response.add("nothing shown here");
            e.printStackTrace();
            return new ResponseEntity<>(MappingResponse.mapUniversalResponse("Error while deleting admin",response), HttpStatus.CREATED);
        }
    }
}
