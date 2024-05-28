package dev.sasikumar.productserviceproject.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDTO {
    private Long orderId;
    private String name;
    private Long amount;
    private String email;
    private String phoneNumber;
}
