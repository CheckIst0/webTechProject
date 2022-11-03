package web.tech.project.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.tech.project.api.core.errorhandlers.IdNotFoundException;
import web.tech.project.api.core.model.OrderDto;
import web.tech.project.api.core.service.OrderService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Operation(summary = "Добавление одного заказа")
    @PostMapping("/add")
    public OrderDto addOrder(@RequestBody OrderDto orderDto) {
        return orderService.addOrder(orderDto);
    }

    @Operation(summary = "Добавление списка заказов")
    @PostMapping("/addOrders")
    public List<OrderDto> addOrders(@RequestBody List<OrderDto> orderDtoList) {
        return orderService.addOrders(orderDtoList);
    }

    @Operation(summary = "Получение заказа по ID записи")
    @GetMapping("/getById/{id}")
    public OrderDto getById(@PathVariable Long id) {
        try {
            return orderService.getById(id);
        } catch (EntityNotFoundException e) {
            throw new IdNotFoundException(id);
        }
    }

    @Operation(summary = "Изменение статуса заказа с указанным ID")
    @PatchMapping("/changeStatus/{id}")
    public void changeStatus (@RequestBody OrderDto orderDto, @PathVariable Long id) {
        try {
            orderService.changeStatus(orderDto, id);
        } catch (EntityNotFoundException e) {
            throw new IdNotFoundException(id);
        }
    }
}
