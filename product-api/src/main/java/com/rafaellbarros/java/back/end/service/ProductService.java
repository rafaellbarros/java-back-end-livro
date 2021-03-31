package com.rafaellbarros.java.back.end.service;

import com.rafaellbarros.java.back.end.model.converter.DTOConverter;
import com.rafaellbarros.java.back.end.model.dto.ProductDTO;
import com.rafaellbarros.java.back.end.model.entity.Product;
import com.rafaellbarros.java.back.end.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAll() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(DTOConverter::productToDTO).collect(Collectors.toList());
    }

    public List<ProductDTO> getProductByCategoryId(Long categoryId) {
        List<Product> products = productRepository.getProductByCategory(categoryId);
        return products.stream().map(DTOConverter::productToDTO).collect(Collectors.toList());
    }

    public ProductDTO findByProductIdentifier(String productIdentifier) {
        Product product = productRepository.findByProductIdentifier(productIdentifier);
        if (product != null) {
            return DTOConverter.productToDTO(product);
        }
        return null;
    }

    public ProductDTO save(ProductDTO productDTO) {
        Product product = productRepository.save(DTOConverter.productToEntity(productDTO));
        return DTOConverter.productToDTO(product);
    }

    public void delete(long ProductId) {
        Optional<Product> Product = productRepository.findById(ProductId);
        if (Product.isPresent()) {
            productRepository.delete(Product.get());
        }
    }
}
