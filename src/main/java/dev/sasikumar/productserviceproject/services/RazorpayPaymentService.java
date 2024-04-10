package dev.sasikumar.productserviceproject.services;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import dev.sasikumar.productserviceproject.DTOs.PaymentRequestDTO;
import dev.sasikumar.productserviceproject.models.Product;
import dev.sasikumar.productserviceproject.models.Sales;
import dev.sasikumar.productserviceproject.repository.ProductRepository;
import dev.sasikumar.productserviceproject.repository.SalesRepository;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service("razorpay")
public class RazorpayPaymentService implements PaymentService{

    private final RazorpayClient razorpayClient;
    private final ProductRepository productRepository;
    private final SalesRepository salesRepository;

    public RazorpayPaymentService(RazorpayClient razorpayClient, ProductRepository productRepository, SalesRepository salesRepository){
        this.razorpayClient = razorpayClient;
        this.productRepository = productRepository;
        this.salesRepository = salesRepository;
    }
    @Override
    public String initiatePayment(PaymentRequestDTO paymentRequestDTO) throws RazorpayException {

        Product product = productRepository.findByIdIs(paymentRequestDTO.getProductId());
        paymentRequestDTO.setAmount(product.getPrice() * paymentRequestDTO.getQuantity());

        Sales sales = new Sales();
        sales.setProductId(paymentRequestDTO.getProductId());
        sales.setQuantity(paymentRequestDTO.getQuantity());
        sales.setAmount(paymentRequestDTO.getAmount());
        sales.setCurrency(paymentRequestDTO.getCurrency());
        salesRepository.save(sales);

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount",paymentRequestDTO.getAmount());
        orderRequest.put("currency",paymentRequestDTO.getCurrency());

        JSONObject notify = new JSONObject();
        notify.put("sms", true);
        notify.put("email", true);
        orderRequest.put("notify", notify);

        JSONObject callBack = new JSONObject();
        callBack.put("callBack_url", "https://ProductServiceProject.com/payments");
        callBack.put("callBack_method", "post");
        orderRequest.put("callBack", callBack);

        PaymentLink response = razorpayClient.paymentLink.create(orderRequest);

        return response.toString();
    }
}
