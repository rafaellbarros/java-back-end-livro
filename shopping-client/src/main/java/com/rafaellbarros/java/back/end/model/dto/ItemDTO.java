package com.rafaellbarros.java.back.end.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ItemDTO {

    @NotBlank
    private String productIdentifier;

    @NotNull
    private Float price;

}
