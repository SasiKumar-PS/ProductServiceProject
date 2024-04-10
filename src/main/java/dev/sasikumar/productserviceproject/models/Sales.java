package dev.sasikumar.productserviceproject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sales {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    private Long productId;
    private int quantity;
    private Double amount;
    private String currency;
}