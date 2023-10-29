package com.sayan.ElectroHub.Repository;

import com.sayan.ElectroHub.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,String> {

}
