package dev.sasikumar.productserviceproject.services;

import com.razorpay.RazorpayException;
import dev.sasikumar.productserviceproject.services.paymentgateway.PaymentGateway;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{

    private  final PaymentGateway paymentGateway;
    public PaymentServiceImpl(PaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }

    @Override
    public String initiatePayment(Long orderId, String name, Long amount, String email, String phoneNumber) throws RazorpayException {
        return paymentGateway.initiatePayment(orderId, name, amount, email, phoneNumber);
    }
}
