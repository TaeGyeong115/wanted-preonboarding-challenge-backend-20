package io.taylor.wantedpreonboardingchallengebackend20.service;

import io.taylor.wantedpreonboardingchallengebackend20.dto.request.ProductOrderRequest;
import io.taylor.wantedpreonboardingchallengebackend20.dto.response.ProductOrderResponse;
import io.taylor.wantedpreonboardingchallengebackend20.dto.response.ProductResponse;
import io.taylor.wantedpreonboardingchallengebackend20.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    public List<Order> getOrders() {
        return null;
    }

    public ProductOrderResponse getOrderById(Long productId) {
        return null;
    }

    public ProductResponse updateOrder(Long orderId, ProductOrderRequest request) {
        return null;
    }
}
