package com.sayan.ElectroHub.Services;

import com.sayan.ElectroHub.DTO.OrderRequest;

public interface OrderServices {
    Object placedOrder(OrderRequest orderRequest);
    Object viewOrder(String orderId);
}
