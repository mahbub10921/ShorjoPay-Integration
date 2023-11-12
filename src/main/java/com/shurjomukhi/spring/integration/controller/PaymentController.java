package com.shurjomukhi.spring.integration.controller;

import com.shurjomukhi.ShurjopayException;
import com.shurjomukhi.spring.integration.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class PaymentController {

    private @Autowired PaymentService service;


    @GetMapping("/pay")
    public ResponseEntity<?> payAmount() throws ShurjopayException {

        return ResponseEntity.ok(service.pay());
    }


    @GetMapping("/verifyPayment/{id}")
    public  ResponseEntity<?> paymentVerification(@PathVariable("id") String id) throws ShurjopayException{

        return ResponseEntity.ok(service.verifyPay(id));

    }




    /**
     *
     * @param model
     * @return root page
     */
//    @GetMapping
//    public String showProductPage(Model model) {
//        Product product = new Product();
//        model.addAttribute("product", product);
//        return "product";
//    }
//
//    @PostMapping("/buy-product")
//    public String buyProduct(@ModelAttribute("product") Product product, Model model) {
//        PaymentRes response = service.buy(product.setPrice(1.0));
//        return "redirect:" + response.getPaymentUrl();
//    }
//
//    /**
//     * Get shurjopay response to get order id and verify payment here
//     * @param orderId
//     * @param model
//     * @return
//     */
//    @GetMapping("/response")
//    public String paymentResponse(@RequestParam("order_id") String orderId, Model model) {
//        if (service.verifyOrder(orderId)) {
//            model.addAttribute("msg", ShurjopayStatus.SHURJOPAY_SUCCESS.message());
//            model.addAttribute("orderId", orderId);
//        } else
//            model.addAttribute("msg", ShurjopayStatus.PAYMENT_FAILED.message());
//        return "status";
//    }
//
//    /**
//     * Checking payment status by order id
//     * @param order id
//     * @param model
//     * @return payment status page to show the current state of payment
//     */
////    @GetMapping("/payment-status/{id}")
////    public String paymentStutus(@PathVariable("id") String id, Model model) {
////        model.addAttribute("paymentStat", service.checkPaymentStatus(id));
////        return "payment-status";
////    }

}
