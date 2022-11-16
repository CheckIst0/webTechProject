package web.tech.project.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Получение заказа по ID записи")
    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable Long id) {
        try {
            return orderService.getById(id);
        } catch (EntityNotFoundException e) {
            throw new IdNotFoundException(id);
        }
    }

    @Operation(summary = "Получение всех заказов")
    @GetMapping("/getAll")
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @Operation(summary = "Изменение статуса заказа с указанным ID")
    @PatchMapping("/{id}/changeStatus")
    public void changeStatus (@RequestBody OrderDto orderDto, @PathVariable Long id) {
        try {
            orderService.changeStatus(orderDto, id);
        } catch (EntityNotFoundException e) {
            throw new IdNotFoundException(id);
        }
    }

    @Operation(summary = "Имитация успешной оплаты заказа")
    @PatchMapping("/{id}/payment/success")
    public String paymentSuccess(@PathVariable Long id) {
        return orderService.paymentSuccess(id);
    }

    @Operation(summary = "Имитация провальной оплаты заказа")
    @PatchMapping("/{id}/payment/error")
    public String paymentError(@PathVariable Long id) {
        return orderService.paymentError(id);
    }
}
