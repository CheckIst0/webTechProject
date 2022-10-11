package web.tech.project.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.tech.project.api.core.model.OrderDto;
import web.tech.project.api.core.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public OrderDto addOrder(@RequestBody OrderDto orderDto) {
        return orderService.addOrder(orderDto);
    }

    @GetMapping("/getById/{id}")
    public OrderDto getById(@PathVariable Long id) {
        return orderService.getById(id);
    }
}
