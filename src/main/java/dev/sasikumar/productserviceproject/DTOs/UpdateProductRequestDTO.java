package dev.sasikumar.productserviceproject.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductRequestDTO {
    private String title;
    private Double price;
    private String category;
    private String description;
    private String image;
}
