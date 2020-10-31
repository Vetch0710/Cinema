package com.Cinema_Management_System.Order.controller;

import com.Cinema_Management_System.Order.entity.PayOrder;
import com.Cinema_Management_System.Order.entity.SqlOrder;
import com.Cinema_Management_System.Order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Qualifier("OrderService")
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
    public PayOrder getOrderInfo(@PathVariable long orderId) {
        return orderService.getOrderInfo(orderId);
    }

    @RequestMapping(value = "/{orderId}/{status}", method = RequestMethod.PUT)
    public String modifyStatus(@PathVariable long orderId, @PathVariable String status) throws UnsupportedEncodingException {
        status = status = new String(status.getBytes("ISO-8859-1"), "UTF-8");
        try {
            orderService.modifyStatus(orderId, status);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping(value = "generateOrder", method = RequestMethod.POST)
    public Map<String, Object> generateOrder(@RequestBody Map<String, Object> dataMap) {
        Object obj1 = dataMap.get("sqlOrder");
        List<String> selectedSeats = (List<String>) dataMap.get("selectedSeat");
        SqlOrder sqlOrder = (SqlOrder) obj1;
        String[] selectedSeat = (String[]) selectedSeats.toArray();
        System.out.println(sqlOrder);
        System.out.println(selectedSeat);
        try {
            return orderService.generateOrder(sqlOrder, selectedSeat);
        } catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "error");
            return map;
        }
    }
//    @RequestMapping(value = "generateOrder", method = RequestMethod.POST)
//    public void generateOrder(@RequestBody Map<String,Object> map) {
//        System.out.println(map);
//
//    }

    @RequestMapping(value = "/getOrderList", method = {RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> getOrderList(@RequestParam(value = "type", required = false) String type,
                                            @RequestHeader("accessToken") String accessToken,
                                            @RequestParam(value = "pageNo", required = false) String pageNo,
                                            @RequestParam(value = "pageSize", required = false) String pageSize,
                                            @RequestParam(value = "selectType", required = false) String selectType,
                                            @RequestParam(value = "selectValues", required = false) String selectValues
    ) throws Exception {
        System.out.println("**************getOrderList***************");
        String identity = accessToken.substring(accessToken.indexOf("-") + 1);
        int userId = Integer.parseInt(accessToken.substring(0, accessToken.indexOf("-")));
        System.out.println(selectValues);
        System.out.println(selectType);
        if (type != null)
            type = new String(type.getBytes("ISO-8859-1"), "UTF-8");
        else if (selectValues != null)
            selectValues = new String(selectValues.getBytes("ISO-8859-1"), "UTF-8");

        if ("全部订单".equals(type)) {
            type = null;
        }
        int n = 0;
        int s = 0;
        if (pageNo != null && pageSize != null) {
            n = Integer.parseInt(pageNo);
            s = Integer.parseInt(pageSize);
        }
        System.out.println(userId + "---" + identity + "----" + type + "---" + s * (n - 1) + "----" + s);
        Map<String, Object> allOrder = orderService.getAllOrder(userId, identity, type, s * (n - 1), s, selectType, selectValues);

        System.out.println(allOrder);

        return allOrder;
    }

}
