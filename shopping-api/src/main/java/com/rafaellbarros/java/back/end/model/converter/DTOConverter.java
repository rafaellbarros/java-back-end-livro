package com.rafaellbarros.java.back.end.model.converter;

import com.rafaellbarros.java.back.end.model.dto.ItemDTO;
import com.rafaellbarros.java.back.end.model.dto.ShopDTO;
import com.rafaellbarros.java.back.end.model.entity.Item;
import com.rafaellbarros.java.back.end.model.entity.Shop;

import java.util.stream.Collectors;

public class DTOConverter {

    public static ItemDTO itemToDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setProductIdentifier(item.getProductIdentifier());
        itemDTO.setPrice(item.getPrice());
        return itemDTO;
    }

    public static ShopDTO shopToDTO(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setUserIdentifier(shop.getUserIdentifier());
        shopDTO.setTotal(shop.getTotal());
        shopDTO.setDate(shop.getDate());
        shopDTO.setItems(shop
                .getItems()
                .stream()
                .map(DTOConverter::itemToDTO)
                .collect(Collectors.toList()));
        return shopDTO;
    }

    public static Item itemToEntity(ItemDTO itemDTO) {
        Item item = new Item();
        item.setProductIdentifier(itemDTO.getProductIdentifier());
        item.setPrice(itemDTO.getPrice());
        return item;
    }

    public static Shop shopToEntity(ShopDTO shopDTO) {
        Shop shop = new Shop();
        shop.setUserIdentifier(shopDTO.getUserIdentifier());
        shop.setTotal(shopDTO.getTotal());
        shop.setDate(shopDTO.getDate());
        shop.setItems(shopDTO
                .getItems()
                .stream()
                .map(DTOConverter::itemToEntity)
                .collect(Collectors.toList()));
        return shop;
    }


}
