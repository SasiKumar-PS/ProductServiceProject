package dev.sasikumar.scalerproject.DTOs;

import dev.sasikumar.scalerproject.models.Category;
import dev.sasikumar.scalerproject.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductRequestDTO {
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
