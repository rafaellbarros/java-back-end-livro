package com.rafaellbarros.java.back.end.service;

import com.rafaellbarros.java.back.end.model.dto.ShopDTO;
import com.rafaellbarros.java.back.end.model.dto.ShopReportDTO;
import com.rafaellbarros.java.back.end.model.entity.Shop;
import com.rafaellbarros.java.back.end.repository.ReportRepository;
import com.rafaellbarros.java.back.end.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private ShopRepository shopRepository;

    public List<ShopDTO> getShopsByFilter(Date dataInicio, Date dataFim, Float valorMinimo) {
        List<Shop> shops = shopRepository.getShopByFilters(dataInicio, dataFim, valorMinimo);
        return shops
                .stream()
                .map(ShopDTO::convert)
                .collect(Collectors.toList());
    }

    public ShopReportDTO getReportByDate(Date dataInicio, Date dataFim) {
        return shopRepository.getReportByDate(dataInicio, dataFim);
    }
}
