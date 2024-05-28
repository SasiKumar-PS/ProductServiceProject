package dev.sasikumar.productserviceproject.services.paymentgateway;

import com.razorpay.RazorpayException;

public interface PaymentGateway {
    String initiatePayment(Long orderId, String name, Long amount, String email, String phoneNumber) throws RazorpayException;
}
