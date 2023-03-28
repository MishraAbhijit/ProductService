package com.ebasket.productservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsResponseDTO {
    Map<String,CategoryResponseDTO> categoryResponse = new HashMap<>();
}
