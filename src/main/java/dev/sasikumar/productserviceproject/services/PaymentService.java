package dev.sasikumar.productserviceproject.services;

import com.razorpay.RazorpayException;

public interface PaymentService {
    String initiatePayment(Long orderId, String name, Long amount, String email, String phoneNumber) throws RazorpayException;
}
