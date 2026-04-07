package com.microservice.order.controller;

import com.base.base.dto.OrderEventDTO;
import com.microservice.order.common.OrderResponse;
import com.microservice.order.dto.OrderDTO;
import com.microservice.order.kafka.OrderProducer;
import com.microservice.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @GetMapping("/getorders")
    public List<OrderDTO> getOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("order/{orderId}")
    public OrderDTO getOrderById(@PathVariable int orderId) {
        return orderService.getOrderById(orderId);
    }

    @PostMapping("addorder")
    public OrderResponse saveOrder(@RequestBody OrderDTO orderDTO) {
        OrderEventDTO orderEventDTO = new OrderEventDTO();
        orderEventDTO.setMessage("Order committed");
        orderEventDTO.setStatus("pending");
        orderProducer.sendMessage(orderEventDTO);
        return orderService.saveOrder(orderDTO);
    }

    @PutMapping("/updateorder")
    public OrderDTO updateOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.updateOrder(orderDTO);
    }

    @DeleteMapping("/deleteorder/{orderId}")
    public String  deleteOrder(@PathVariable Integer orderId) {
        return orderService.deleteOrder(orderId);
    }


}
