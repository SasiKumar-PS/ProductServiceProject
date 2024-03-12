package dev.sasikumar.productserviceproject.DTOs;

import dev.sasikumar.productserviceproject.models.Category;
import dev.sasikumar.productserviceproject.models.Product;
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

    public Product toProduct(){
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImageUrl(image);

        Category category1 = new Category();
        category1.setTitle(category);
        product.setCategory(category1);

        return product;
    }
}


