package com.rafaellbarros.java.back.end.repository;

import com.rafaellbarros.java.back.end.model.dto.ShopReportDTO;
import com.rafaellbarros.java.back.end.model.entity.Shop;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

public interface ReportRepository {

    public List<Shop> getShopByFilters(Date dataInicio, Date dataFim, Float valorMinimo);
    public ShopReportDTO getReportByDate(Date dataInicio, Date dataFim);

}
