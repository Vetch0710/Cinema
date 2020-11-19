package com.Cinema_Management_System.Order.controller;

import com.Cinema_Management_System.Order.entity.PayOrder;
import com.Cinema_Management_System.Order.service.OrderService;
import com.Cinema_Management_System.Order.service.PayService;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Description class
 * @Auther GaoYi
 * @Date 2020/10/31 10:49 下午
 */
@Controller
public class PayController {
    @Qualifier("PayService")
    @Autowired
    private PayService payService;

    @Qualifier("OrderService")
    @Autowired
    private OrderService orderService;

    private final String APP_ID = "2016102500757502";
    private final String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCBHaaY6YA03jWSSVFovIeuGWJl5BuQfHaWzZmIKIw9Cm9HoWa6dnMUe5GGPCMQfHmmLvgHTQ1ATyQPdwFTW20S6fc8+6TUED1kZjqHtR3MVpFUhSrYNyHXsHTcqzvi2kuQPqAcZ3GcQoxkxva2e/nRdT+ZvOHAXC984UCOwFB4EzzGEoGKuIlAefbftSn5JhS/L2JLI8EWt/x9dqIMxwaDdIyx3aIuDJKQrvhQQJzk2Nup7TxHMfViqxbLughh9Cy6N3NODQtEGnpWdB4qAgrmTrqtgm4aH5UscEKFe/9d9sdrqJ5AFcIIAkFR1QGp37+/fgvocylF/koPeInRU4oTAgMBAAECggEAQSvuQ5DZHdZrxAMuDGfdNIurlzKklzG7yjLNJGUTJFx3/vgBGm1feR9GPTNtn4y0+qUKnOTPo35O/V9cU+76B2oBWc7LTB9GXb8cHBPXjqTDhp4RGkH52x0+X4QvHjqBmbWCq9OrDOyqYNPtA/9gAdHk4HzulmHxtl8cbvGzrGeR0cDMXSblSC2Nj6ubxhCUkoBOP7yu2MSyB+utyyAj/x3rIIIq14tOgOtk1q3vbEsgS5DEC6KtITH1Lhgo2Z6je7RNo1X6ankAij2oQPVP/zFfCYdv4JhqlVCfoqG/zaoiirbCAMR1qvEltSjqK3DzIvfoYEpFBmii5EV0hP3ggQKBgQC67YswOIkvgXeBu7t8pxBbEvmtGzsS/KEADFssCY95by8j+YrZXBNqtbEbtb3f0AfZq289FSIlgA5XBFogSGPaRKSc5HG/VCwlFniObKsp1Q/AhOORFaQszHZ2qlBhKxhFeKMehlnmu4Z6ZyKwQJW5x+/RIqLevr6jyeQgcbL6SwKBgQCw016oMoYBp5XiT21sTTjeu7YqkbRwReUgYEkLeUvdUiq1UMScqi3bTSPUigVibVQpIbYHZUWLYdhxhgSI7fuS0/0gpWJbOcOHjt7gqtjSa+8wLyIKdab31tGzr5MdDtJ+0PXsQiJ0WKCL/ba4BnSwz0pJsQ2acxV6doxbbLvSWQKBgHqSlTiTefF6tcCON4yvh9wQf0PxnTUADxqW/5nK0xfPvluaoF+TLvBEH9SJwe3m3Z7v+UtWdkOcyspogii1DzTq2f4/Fg9dxGy1ZWwO+dW+bkvRV9+lDziJAg0h9P8iYRH83wl/E6hmwvG0e1yTnRprbmf2jFH/Rcbk9gAITlItAoGAR8Zkg82OS5mk42nuNqnZzb9QIFNodLJKYSv0Om4P91Rk9ZhFVnpVhtD3HEyyBtPt94wxgSsAYaVj4CDQMmy3U1AtTVUUaYc28Q1zNqriu/cH7f0Tyj8beTp/gHpiMsdTdhqH2FC3/5YCBREFXAly0Fo0A1mU9f6E12LLckjerHECgYBvaQOXmyL/rl+FHIj94u2nHeJMdyAvQBawctpxYhxYRNnHHiAOKndvgsF4LqplUHzL8dn4+GhLikW4jcVozIqUjuI/dV78WBdrpHhi+uFbwB4MIr3GThu4R/cXcnvs2ygQOtYltQCOkTOxVYuHm07jNylIPPLqLbOlddVizVy3PQ==";
    private final String CHARSET = "UTF-8";
    private final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjdNJS+t06O8bBr7RM3N8V6F3vmItZFk9DEUWO7LFLsuICEgC7SgheKDAlReDkqOIGgB/Kfp8sqnU+H74Al0B5j1NiIbfDHKeYTW5gw5mCLPqzecUeqlxh6mOXGa7jgH+yU0XCizolxhMWBBBJSV3dtiLFofUrw9J7Bbaj+VuolFqLpngE+mXcCJYSTz55pD0G7BJfYgYi7YbeIBEVaRVpy6SZtwTvdCZlu76wtbOjpSEhGOa+w1OD6XJlvNYC4kcZacWZppIqnAqbnBo7533ug3PfoQmEyGHTcjLj7OJMzacQKN9+8wQZeIln0m9z6Lq6tr/1/4Zd51nPvU0ZlUKzQIDAQAB";
    //这是沙箱接口路径,正式路径为https://openapi.alipay.com/gateway.do
    private final String GATEWAY_URL = "https://openapi.alipaydev.com/gateway.do";
    private final String FORMAT = "JSON";
    //签名方式
    private final String SIGN_TYPE = "RSA2";
    //支付宝异步通知路径,付款完毕后会异步调用本项目的方法,必须为公网地址
    private final String NOTIFY_URL = "http://8.131.77.164:8089/CinemaData/notifyUrl";
    //支付宝同步通知路径,也就是当付款完毕后跳转本项目的页面,可以不是公网地址
    private final String RETURN_URL = "http://8.131.77.164:8089/CinemaData/returnUrl";

    @RequestMapping("alipay")
    public void alipay(@RequestBody String orderId, HttpServletResponse httpResponse) throws IOException {
        //实例化客户端,填入所需参数
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //在公共参数中设置回跳和通知地址
        request.setReturnUrl(RETURN_URL);
        request.setNotifyUrl(NOTIFY_URL);
        //根据订单编号,查询订单相关信息
        System.out.println(orderId);
        PayOrder order = payService.getOrderInfo(Long.parseLong(orderId));
        System.out.println(order);
        //商户订单号，商户网站订单系统中唯一订单号，必填
        long out_trade_no = order.getOrderId();
        //付款金额，必填
        float total_amount = order.getOrderPrice();
        //订单名称，必填
        String subject = order.getMovieName() + "第" + order.getArrangementId() + "场" + order.getOrderSeat();
        //商品描述，可空
        String body = "";
        request.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String form = "";
        try {
            form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    @RequestMapping(value = "/returnUrl", method = RequestMethod.GET)
    public String returnUrl(HttpServletRequest request, HttpServletResponse response) throws IOException, AlipayApiException {
        System.out.println("=================================同步回调=====================================");
// 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("utf-8"), "utf-8");
            params.put(name, valueStr);
        }

        System.out.println(params);//查看参数都有哪些
        boolean signVerified = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, SIGN_TYPE); // 调用SDK验证签名
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
//验证签名通过
        if (signVerified) {
            // 商户订单号

            // 支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // 付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

            System.out.println("商户订单号=" + out_trade_no);
            System.out.println("支付宝交易号=" + trade_no);
            System.out.println("付款金额=" + total_amount);

            //支付成功，修复支付状态
            try {
                System.out.println("支付成功");
                orderService.modifyStatus(Long.parseLong(out_trade_no), "待评价");
                return "redirect:" + "http://8.131.77.164:8089/Cinema/#/ticket/orderFinished/" + out_trade_no;//跳转付款失败页面

            } catch (Exception e) {
                System.out.println("支付失败");
                e.printStackTrace();
                return "redirect:" + "http://8.131.77.164:8089/Cinema/#/ticket/orderFinished/" + out_trade_no;//跳转付款失败页面

            }
        } else {
            System.out.println("支付失败1");
            return "redirect:" + "http://8.131.77.164:8089/Cinema/#/ticket/orderFinished/" + out_trade_no;//跳转付款失败页面
        }
    }
}
