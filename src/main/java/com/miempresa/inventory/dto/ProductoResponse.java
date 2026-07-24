package com.miempresa.inventory.dto;

import java.math.BigDecimal;

public record ProductoResponse(
        Long id,
        String nombre,
        BigDecimal precio) {
}
