package com.sayan.ElectroHub.Services;

import com.sayan.ElectroHub.Model.Admin;

public interface AdminServices {
    Object saveAdmin(Admin admin);
    Object viewAdmin(String id) throws Exception;
    Object updateAdmin(Admin admin);
    String deleteAdmin(String id);
}
