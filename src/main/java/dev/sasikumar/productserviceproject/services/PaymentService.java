package dev.sasikumar.productserviceproject.services;

import com.razorpay.RazorpayException;
import dev.sasikumar.productserviceproject.DTOs.PaymentRequestDTO;

public interface PaymentService {
    String initiatePayment(PaymentRequestDTO paymentRequestDTO) throws RazorpayException;
}
