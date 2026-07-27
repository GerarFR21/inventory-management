package com.miempresa.inventory.service;

import com.miempresa.inventory.dto.ProductoRequest;
import com.miempresa.inventory.dto.ProductoResponse;
import com.miempresa.inventory.entity.Producto;
import com.miempresa.inventory.mapper.ProductoMapper;
import com.miempresa.inventory.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository repository;
    private final ProductoMapper mapper;

    @Override
    public List<ProductoResponse> findAll() {

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public ProductoResponse findById(Long id) {

        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow();
    }

    @Override
    public ProductoResponse create(ProductoRequest request) {

        Producto savedProducto = repository.save(mapper.toEntity(request));

        return mapper.toResponse(savedProducto);
    }

    @Override
    public ProductoResponse update(Long id, ProductoRequest request) {

        Producto foundProduct = repository.findById(id)
                .orElseThrow();

        foundProduct.setNombre(request.nombre());
        foundProduct.setPrecio(request.precio());

        return mapper.toResponse(repository.save(foundProduct));
    }

    @Override
    public void delete(Long id) {

        repository.deleteById(id);
    }
}
