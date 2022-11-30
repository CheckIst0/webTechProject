package web.tech.project.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import web.tech.project.api.core.errorhandlers.IdNotFoundException;
import web.tech.project.api.core.model.OrderDto;
import web.tech.project.api.core.model.StatusDto;
import web.tech.project.api.core.service.OrderService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    private Long id;
    private Thread run;

    private Thread createThread() {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                StatusDto statusDto = new StatusDto();
                statusDto.setId(3L);
                OrderDto orderDto = new OrderDto();
                orderDto.setStatus(statusDto);
                RestTemplate restTemplate = new RestTemplate();
                HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
                requestFactory.setConnectTimeout(5000);
                requestFactory.setReadTimeout(5000);
                restTemplate.setRequestFactory(requestFactory);
                try {
                    Thread.sleep(1000 * 60 * 1);
                    System.out.println("asdf");
                    restTemplate.patchForObject("http://localhost:8080/api/order/" + id + "/changeStatus", orderDto, Object.class);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Operation(summary = "Добавление одного заказа")
    @PostMapping("/add")
    public OrderDto addOrder(@RequestBody OrderDto orderDto) {
        run = createThread();
        OrderDto res = orderService.addOrder(orderDto);
        this.id = res.getId();
        run.start();
        return res;
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
    public void changeStatus(@RequestBody OrderDto orderDto, @PathVariable Long id) {
        try {
            orderService.changeStatus(orderDto, id);
        } catch (EntityNotFoundException e) {
            throw new IdNotFoundException(id);
        }
    }

    @Operation(summary = "Имитация успешной оплаты заказа")
    @PatchMapping("/{id}/payment/success")
    public OrderDto paymentSuccess(@PathVariable Long id) {
        if (run.isAlive()) {
            run.interrupt();
        }
        return orderService.paymentSuccess(id);
    }

    @Operation(summary = "Имитация провальной оплаты заказа")
    @PatchMapping("/{id}/payment/error")
    public OrderDto paymentError(@PathVariable Long id) {
        if (run.isAlive()) {
            run.interrupt();
        }
        return orderService.paymentError(id);
    }
}