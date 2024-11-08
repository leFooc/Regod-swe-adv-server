package com.regod.app.service;

import com.regod.app.dto.request.CreateOrderDto;
import com.regod.app.dto.request.ModifyRequestDto;
import com.regod.app.entity.Order;
import com.regod.app.repositories.OrderRepository;
import com.regod.app.utils.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository requestRepository;

    public List<Order> findAll() {
        return requestRepository.findAll();
    }

    public Order find(String id) {
        Optional<Order> res = requestRepository.findById(id);

        if (res.isEmpty()) {
            throw new NotFoundException("Request not found");
        }

        return res.get();
    }

    public Order create(CreateOrderDto data) {
        Order request = new Order(
                data.getData()
        );

        return requestRepository.save(request);
    }

    public void updateById(String id, ModifyRequestDto data) {
        Optional<Order> request = requestRepository.findById(id);

        if (request.isEmpty()) {
            throw new NotFoundException("Request not found");
        }

        Order res = request.get();
        if (data.getData() != null)
            res.setData(data.getData());

        requestRepository.save(res);
    }

    public void deleteById(String id) {
        Optional<Order> request = requestRepository.findById(id);

        if (request.isEmpty()) {
            throw new NotFoundException("Request not found");
        }

        requestRepository.deleteById(id);
    }
}
