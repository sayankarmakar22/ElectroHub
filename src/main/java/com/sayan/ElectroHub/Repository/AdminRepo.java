package com.sayan.ElectroHub.Repository;

import com.sayan.ElectroHub.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin,String> {
}
