package com.sayan.ElectroHub.Services.Implementation;

import com.sayan.ElectroHub.DTO.ProductRequest;
import com.sayan.ElectroHub.DTO.ProductUpdate;

import com.sayan.ElectroHub.Helper.ProductHelper;
import com.sayan.ElectroHub.Model.Product;
import com.sayan.ElectroHub.Repository.ProductRepo;
import com.sayan.ElectroHub.Services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServicesImpl implements ProductServices {
    @Autowired
    private ProductRepo productRepo;


    @Override
    public Product saveProduct(ProductRequest product) throws Exception {
        return productRepo.save(ProductHelper.convertSavedProductToProduct(product,new Product()));
    }

    @Override
    public Product updateProduct(ProductUpdate product) throws Exception {
        if(productRepo.existsByproductId(product.getProductId())){
            productRepo.save(ProductHelper.convertSavedProductToProductUpdate(product,productRepo.findByproductId(product.getProductId())));
            return productRepo.findByproductId(product.getProductId());
        }
        throw new RuntimeException("error while updating product");
    }

    @Override
    public Object viewProduct(String productId)  {
       if(productRepo.existsByproductId(productId)){
           return productRepo.findByproductId(productId);
       }
       throw new RuntimeException("product not found");
    }

    @Override
    public String deleteProduct(String productId) throws Exception {
        if (productRepo.existsByproductId(productId)) {
            productRepo.deleteById(productId);
            return "product has been deleted and the id is : " + productId;
        }
        throw new RuntimeException("error while deleting product");


    }
}
