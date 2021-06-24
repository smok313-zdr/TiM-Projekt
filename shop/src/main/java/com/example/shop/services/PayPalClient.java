package com.example.shop.services;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PayPalClient {
    String clientId = "AR3z2TSR3MdJ-DwDZ6PXNZO7xjq0xJKR1NhwZS0zjO3R5GkWwJT_HtYTCM0F9lLi4qnVz2xu6UM7inWI";
    String clientSecret = "EFfSvTHHrV24sw-Of71xq8G5MdNxijAHOq3BG9lD1oxZBpvt3Daw9rxNvR6-K-fwykRmldBRoXeSoPsQ";

//    @Autowired
//    PayPalClient(){}

    public Map<String, Object> createPayment(Double sum){
        Map<String, Object> response = new HashMap<String, Object>();
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(sum.toString());
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:4200/cancel");
        redirectUrls.setReturnUrl("http://localhost:4200/shop");
        payment.setRedirectUrls(redirectUrls);
        Payment createdPayment;
        try {
            String redirectUrl = "";
            APIContext context = new APIContext(clientId, clientSecret, "sandbox");
            createdPayment = payment.create(context);
            if(createdPayment!=null){
                List<Links> links = createdPayment.getLinks();
                for (Links link:links) {
                    if(link.getRel().equals("approval_url")){
                        redirectUrl = link.getHref();
                        break;
                    }
                }
                response.put("status", "success");
                response.put("redirect_url", redirectUrl);
            }
        } catch (PayPalRESTException e) {
            System.out.println("Error happened during payment creation!");
        }
        return response;
    }

    public Map<String, Object> createAndroidPayment(Double sum, String token){
        Map<String, Object> response = new HashMap<String, Object>();
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(sum.toString());
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("app://open.shop.login/login.app");
        redirectUrls.setReturnUrl("app://open.shop.login/login.app?tokenAPI="+token);
        payment.setRedirectUrls(redirectUrls);
        Payment createdPayment;
        try {
            String redirectUrl = "";
            APIContext context = new APIContext(clientId, clientSecret, "sandbox");
            createdPayment = payment.create(context);
            if(createdPayment!=null){
                List<Links> links = createdPayment.getLinks();
                for (Links link:links) {
                    if(link.getRel().equals("approval_url")){
                        redirectUrl = link.getHref();
                        break;
                    }
                    if(link.getRel().equals("approval_url")){
                        redirectUrl = link.getHref();
                        break;
                    }
                }
                response.put("status", "success");
                response.put("redirect_url", redirectUrl);
            }
        } catch (PayPalRESTException e) {
            System.out.println("Error happened during payment creation!");
        }
        return response;
    }


    public Map<String, Object> completePayment(HttpServletRequest req){
        Map<String, Object> response = new HashMap();
        Payment payment = new Payment();
        payment.setId(req.getParameter("paymentId"));
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(req.getParameter("payerId"));
        try {
            APIContext context = new APIContext(clientId, clientSecret, "sandbox");
            Payment createdPayment = payment.execute(context, paymentExecution);
            if(createdPayment!=null){
                response.put("status", "success");
                response.put("payment", createdPayment);
            }
        } catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
        }
        return response;
    }

    public Map<String, Object> completeAndroidPayment(String paymentId, String payerId){
        Map<String, Object> response = new HashMap();
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
        APIContext context = new APIContext(clientId, clientSecret, "sandbox");
        Payment createdPayment = null;
        try {
            createdPayment = payment.execute(context, paymentExecution);
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        if(createdPayment!=null){
            response.put("status", "success");
            response.put("payment", createdPayment.toJSON());
        }
        return response;
    }


}
