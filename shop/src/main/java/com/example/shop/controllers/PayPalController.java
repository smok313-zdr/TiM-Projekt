package com.example.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.shop.services.PayPalClient;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/paypal")
public class PayPalController {
    private final PayPalClient payPalClient;
    @Autowired
    PayPalController(PayPalClient payPalClient){
        this.payPalClient = payPalClient;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/make_payment")
    @PreAuthorize("hasRole('USER')")
    public Map<String, Object> makePayment(@RequestParam("sum") Double sum){
        return payPalClient.createPayment(sum);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/android/make_payment")
    @PreAuthorize("hasRole('USER')")
    public Map<String, Object> androidMakePayment(@RequestParam("sum") Double sum, @RequestParam("token") String token){
        return payPalClient.createAndroidPayment(sum,token);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/complete_payment")
    @PreAuthorize("hasRole('USER')")
    public Map<String, Object> completePayment(HttpServletRequest request, @RequestParam("paymentId") String paymentId, @RequestParam("payerId") String payerId){
        return payPalClient.completePayment(request);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/android/complete_payment")
    @PreAuthorize("hasRole('USER')")
    public Map<String, Object> androidCompletePayment(@RequestParam("paymentId") String paymentId, @RequestParam("payerId") String payerId){
        return payPalClient.completeAndroidPayment(paymentId,payerId);
    }


}
