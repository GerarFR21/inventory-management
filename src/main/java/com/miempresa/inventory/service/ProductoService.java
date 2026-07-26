package com.miempresa.inventory.service;

import com.miempresa.inventory.dto.ProductoRequest;
import com.miempresa.inventory.dto.ProductoResponse;

import java.util.List;

public interface ProductoService {

    List<ProductoResponse> findAll();

    ProductoResponse findById(Long id);

    ProductoResponse create(ProductoRequest request);

    ProductoResponse update(Long id, ProductoRequest request);

    void delete(Long id);
}
