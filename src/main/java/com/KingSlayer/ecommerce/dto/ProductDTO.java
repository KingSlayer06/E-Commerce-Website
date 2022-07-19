package com.KingSlayer.ecommerce.dto;

import com.KingSlayer.ecommerce.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class ProductDTO {
    private Long id;

    private String name;

    private int categoryId;

    private Double price;

    private Double weight;

    private String description;

    private String imageName;
}
