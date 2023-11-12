package com.shurjomukhi.spring.integration.service;

import com.shurjomukhi.Shurjopay;
import com.shurjomukhi.ShurjopayException;
import com.shurjomukhi.ShurjopayStatus;
import com.shurjomukhi.model.PaymentReq;
import com.shurjomukhi.model.VerifiedPayment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.server.NotAcceptableStatusException;

@Service
@Slf4j
public class PaymentService {

    private @Autowired Shurjopay shurjopay;


    @Value("${shurjopay.prefix}")
    private String spPrefix;
    private final String CURRENCY = "BDT";
    private final String ADDRESS = "Khilgaon, Dhaka, Bangladesh";

    @Value("${shurjopay.price}")
    private double price;

    public String pay() throws ShurjopayException {

        var paymentRes = shurjopay.makePayment(preparePaymentReq());

        return paymentRes.getPaymentUrl();
    }

    private PaymentReq preparePaymentReq() {

        var request = new PaymentReq();

        request.setPrefix(spPrefix);
        request.setAmount(price);
        request.setCustomerName("Mahbubul Haque");
        request.setCustomerOrderId("sp654b37b771e6s");
        request.setCustomerEmail("shohag7421@gmail.com");
        request.setCustomerPhone("01537263505");
        request.setCustomerCity("Dhaka");
        request.setCustomerAddress(ADDRESS);
        request.setCurrency(CURRENCY);

        return request;

    }




    public VerifiedPayment verifyPay(String TXId) throws ShurjopayException {

        return shurjopay.verifyPayment(TXId);

    }











    public boolean verifyOrder(String orderId) {
        if (orderId.isBlank()) throw new NotAcceptableStatusException("Order id cann't be empty to verify payment.");
        if (log.isDebugEnabled()) log.debug("Requesting to verify payment using {} order id", orderId);
        try {
            var verifiedPayment = shurjopay.verifyPayment(orderId);
            if (log.isDebugEnabled()) log.debug("Verify Payment response: {}", verifiedPayment);

            return verifiedPayment.getSpStatusCode().equals(ShurjopayStatus.SHURJOPAY_SUCCESS.code());
        } catch (ShurjopayException e) {
            log.error("Shurjopay exception occurred while verifying payment", e);
            return false;
        }
    }

//    public VerifiedPayment checkPaymentStatus(String orderId) {
//        if (orderId.isBlank()) throw new NotAcceptableStatusException("Order id cann't be empty to verify payment.");
//        if (log.isDebugEnabled(null)) log.debug("Requesting to verify payment using {} order id", orderId);
//
//        try {
//            var verifiedPayment = shurjopay.checkPaymentStatus(orderId);
//            if (log.isDebugEnabled()) log.debug("Checking payment status response: {}", verifiedPayment);
//
//            return shurjopay.checkPaymentStatus(orderId);
//        } catch (ShurjopayException e) {
//            log.error("Shurjopay exception occurred while checking payment status", e);
//            return null;
//        }
//    }
}
