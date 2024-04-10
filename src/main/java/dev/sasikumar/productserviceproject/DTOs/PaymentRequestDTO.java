package dev.sasikumar.productserviceproject.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDTO {
    private Long productId;
    private int quantity;
    private Double amount;
    private String currency;
    private String email;
    private String phoneNumber;
}
