package web.tech.project.api.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import web.tech.project.api.core.mapper.OrderMapper;
import web.tech.project.api.core.mapper.StatusMapper;
import web.tech.project.api.core.model.OrderDto;
import web.tech.project.db.entity.Meal;
import web.tech.project.db.entity.Order;
import web.tech.project.db.entity.Status;
import web.tech.project.db.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private StatusMapper statusMapper;
    @Autowired
    private TableService tableService;

    public OrderDto addOrder(OrderDto order) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put("http://localhost:8080/api/table/" + order.getTable().getId() + "/takeATable", null);
        Order orderNew = orderMapper.map(order, Order.class);
        orderNew = orderRepository.save(orderNew);
        return orderMapper.map(orderNew, OrderDto.class);
    }

    public OrderDto getById(Long id) {
        Order order = orderRepository.getReferenceById(id);
        List<Meal> meals = order.getMeals();
        for (int i = 0; i < meals.size(); i++) {
            meals.get(i).getMenu().setImage(null);
            meals.get(i).getMenu().setCategory(null);
            meals.get(i).setOrder(null);
        }
        order.setMeals(meals);
        return orderMapper.map(order, OrderDto.class);
    }

    public void changeStatus(OrderDto orderDto, Long id) {
        Order order = orderRepository.getReferenceById(id);
        order.setStatus(statusMapper.map(orderDto.getStatus(), Status.class));
        orderRepository.save(order);
    }

    public List<OrderDto> getAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        for (int i = 0; i < orderList.size(); i++) {
            List<Meal> meals = orderList.get(i).getMeals();
            for (int j = 0; j < meals.size(); j ++) {
                meals.get(j).setOrder(null);
                meals.get(i).getMenu().setCategory(null);
                meals.get(j).getMenu().setImage(null);
            }
            orderList.get(i).setMeals(meals);
        }
        return orderMapper.mapAsList(orderList, OrderDto.class);
    }

    public OrderDto paymentSuccess(Long id) {
        orderRepository.paymentSuccess(id);
        Order order = orderRepository.getReferenceById(id);
        tableService.freeTable(order.getTable().getId());
        return orderMapper.map(order, OrderDto.class);
    }

    public OrderDto paymentError(Long id) {
        orderRepository.paymentError(id);
        Order order = orderRepository.getReferenceById(id);
        return orderMapper.map(order, OrderDto.class);
    }
}