package com.miempresa.inventory.service;

import com.miempresa.inventory.dto.ProductoRequest;
import com.miempresa.inventory.dto.ProductoResponse;
import com.miempresa.inventory.entity.Producto;
import com.miempresa.inventory.mapper.ProductoMapper;
import com.miempresa.inventory.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService{

    private final ProductoRepository repository;
    private final ProductoMapper mapper;

    public ProductoServiceImpl(ProductoRepository repository, ProductoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<ProductoResponse> findAll() {

        return List.of();
    }

    @Override
    public ProductoResponse findById(Long id) {

        return null;
    }

    @Override
    public ProductoResponse create(ProductoRequest request) {

        return null;
    }

    @Override
    public ProductoResponse update(Long id, ProductoRequest request) {

        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
