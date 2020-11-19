package com.Cinema_Management_System.Order.controller;

import com.Cinema_Management_System.Order.entity.PayOrder;
import com.Cinema_Management_System.Order.entity.SqlOrder;
import com.Cinema_Management_System.Order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
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

    @RequestMapping(value = "/getOrderStatus/{orderId}", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public String getOrderStatus(@PathVariable long orderId) {
        System.out.println("===========" + orderId);
        return orderService.getOrderStatus(orderId);
    }

    @RequestMapping(value = "/{orderId}", method = RequestMethod.DELETE)
    public String delOrder(@PathVariable long orderId) {
        String message = orderService.delOrder(orderId);
        return message;
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
        List<Object> objects = (List<Object>) dataMap.get("selectedSeat");
        String strings = "";
        for (Object obj : objects) {
            if (strings.equals("")) {
                strings = obj.toString();
            } else {
                strings = strings + "," + obj.toString();
            }
        }
        String[] selectedSeat = strings.split(", ");
        System.out.println(Arrays.toString(selectedSeat));
        Map<String, Object> sqlMap = (Map<String, Object>) dataMap.get("sqlOrder");
        SqlOrder sqlOrder = new SqlOrder();
        sqlOrder.setArrangementId(Integer.parseInt(sqlMap.get("arrangementId").toString()));
        sqlOrder.setCustomerId(Integer.parseInt(sqlMap.get("customerId").toString()));
        sqlOrder.setMovieId((Integer) sqlMap.get("movieId"));
        sqlOrder.setOrderPrice(Float.parseFloat(sqlMap.get("orderPrice").toString()));
        System.out.println(sqlOrder);
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