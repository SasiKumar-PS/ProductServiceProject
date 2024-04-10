package dev.sasikumar.productserviceproject.controllers;

import com.razorpay.RazorpayException;
import dev.sasikumar.productserviceproject.DTOs.PaymentRequestDTO;
import dev.sasikumar.productserviceproject.services.PaymentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(@Qualifier("razorpay") PaymentService paymentService){
        this.paymentService = paymentService;
    }
    @PostMapping("/payment")
    public String initiatePayment(@RequestBody PaymentRequestDTO paymentRequestDTO) throws RazorpayException{
        return paymentService.initiatePayment(paymentRequestDTO);
    }
}
