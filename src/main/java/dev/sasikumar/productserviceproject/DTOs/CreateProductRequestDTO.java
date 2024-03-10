package dev.sasikumar.productserviceproject.DTOs;

import lombok.Getter;
import lombok.Setter;


// while sending request, we need to use our own custom DTO object,
// for create request, we are using this DTO as a request body
@Getter
@Setter
public class CreateProductRequestDTO {
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}


