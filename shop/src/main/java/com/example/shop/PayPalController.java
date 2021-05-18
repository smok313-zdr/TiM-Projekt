package com.example.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.shop.PayPalClient;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "shop/paypal")
public class PayPalController {
    private final PayPalClient payPalClient;
    @Autowired
    PayPalController(PayPalClient payPalClient){
        this.payPalClient = payPalClient;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/make/payment")
    public Map<String, Object> makePayment(@RequestParam("sum") String sum){
        return payPalClient.createPayment(sum);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/complete/payment")
    public Map<String, Object> completePayment(HttpServletRequest request, @RequestParam("paymentId") String paymentId, @RequestParam("payerId") String payerId){
        return payPalClient.completePayment(request);
    }
}
