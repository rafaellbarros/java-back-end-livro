package com.rafaellbarros.java.back.end.service;

import com.rafaellbarros.java.back.end.exception.ProductNotFoundException;
import com.rafaellbarros.java.back.end.exception.UserNotFoundException;
import com.rafaellbarros.java.back.end.model.converter.DTOConverter;
import com.rafaellbarros.java.back.end.model.dto.ItemDTO;
import com.rafaellbarros.java.back.end.model.dto.ProductDTO;
import com.rafaellbarros.java.back.end.model.dto.ShopDTO;
import com.rafaellbarros.java.back.end.model.dto.UserDTO;
import com.rafaellbarros.java.back.end.model.entity.Shop;
import com.rafaellbarros.java.back.end.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    public List<ShopDTO> getAll() {
        List<Shop> shops = shopRepository.findAll();
        return shops.stream().map(DTOConverter::shopToDTO).collect(Collectors.toList());
    }

    public List<ShopDTO> getByUser(String userIdentifier) {
        List<Shop> shops = shopRepository.findAllByUserIdentifier(userIdentifier);
        return shops.stream().map(DTOConverter::shopToDTO).collect(Collectors.toList());
    }

    public List<ShopDTO> getByDate(ShopDTO shopDTO) {
        List<Shop> shops = shopRepository.findAllByDateGreaterThan(shopDTO.getDate());
        return shops.stream().map(DTOConverter::shopToDTO).collect(Collectors.toList());
    }

    public ShopDTO findById(long ProductId) {
        Optional<Shop> shop = shopRepository.findById(ProductId);
        if (shop.isPresent()) {
            return DTOConverter.shopToDTO(shop.get());
        }
        throw new ProductNotFoundException();
    }

    public ShopDTO save(ShopDTO shopDTO, String key) {

        UserDTO userDTO = userService
                .getUserByCpf(shopDTO.getUserIdentifier(), key);

        validateProducts(shopDTO.getItems());
        shopDTO.setTotal(shopDTO.getItems()
                .stream()
                .map(x -> x.getPrice())
                .reduce((float) 0, Float::sum));

        Shop shop = DTOConverter.shopToEntity(shopDTO);
        shop.setDate(new Date());
        shop = shopRepository.save(shop);
        return DTOConverter.shopToDTO(shop);
    }

    private boolean validateProducts(List<ItemDTO> items) {
        for (ItemDTO item : items) {
            ProductDTO productDTO = productService.getProductByIdentifier(item.getProductIdentifier());
            if (productDTO == null) {
                return false;
            }
            item.setPrice(productDTO.getPreco());
        }
        return true;
    }
}
