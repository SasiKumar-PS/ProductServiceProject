package dev.sasikumar.productserviceproject.controllers;

import com.razorpay.RazorpayException;
import dev.sasikumar.productserviceproject.DTOs.PaymentRequestDTO;
import dev.sasikumar.productserviceproject.services.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }
    @PostMapping("/")
    public String initiatePayment(@RequestBody PaymentRequestDTO requestDTO) throws RazorpayException{
        return paymentService.initiatePayment(requestDTO.getOrderId(), requestDTO.getName(), requestDTO.getAmount(), requestDTO.getEmail(), requestDTO.getPhoneNumber());
    }
}
