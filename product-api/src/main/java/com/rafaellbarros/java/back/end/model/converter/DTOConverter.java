package com.rafaellbarros.java.back.end.model.converter;

import com.rafaellbarros.java.back.end.model.dto.CategoryDTO;
import com.rafaellbarros.java.back.end.model.dto.ProductDTO;
import com.rafaellbarros.java.back.end.model.entity.Category;
import com.rafaellbarros.java.back.end.model.entity.Product;

public class DTOConverter {

    public static CategoryDTO categoryToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setNome(category.getNome());
        return categoryDTO;
    }
    public static ProductDTO productToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductIdentifier(product.getProductIdentifier());
        productDTO.setNome(product.getNome());
        productDTO.setPreco(product.getPreco());
        productDTO.setDescricao(product.getDescricao());
        if (product.getCategory() != null) {
            productDTO.setCategory(DTOConverter.categoryToDTO(product.getCategory()));
        }
        return productDTO;
    }

    public static Category categoryToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setNome(categoryDTO.getNome());
        return category;
    }

    public static Product productToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductIdentifier(product.getProductIdentifier());
        product.setNome(productDTO.getNome());
        product.setPreco(productDTO.getPreco());
        product.setDescricao(productDTO.getDescricao());
        product.setProductIdentifier(
                productDTO.getProductIdentifier());
        if (productDTO.getCategory() != null) {
            product.setCategory(DTOConverter.categoryToEntity(productDTO.getCategory()));
        }
        return product;
    }
}
