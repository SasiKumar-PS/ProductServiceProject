package dev.sasikumar.productserviceproject.repository.projections;

import dev.sasikumar.productserviceproject.models.Category;

public interface ProductProjection {
    Long getId();
    String getTitle();
    String getDescription();
    String getImageUrl();
    Category getCategory();
}
