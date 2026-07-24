package com.miempresa.inventory.mapper;

import com.miempresa.inventory.dto.ProductoRequest;
import com.miempresa.inventory.dto.ProductoResponse;
import com.miempresa.inventory.entity.Producto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    Producto toEntity(ProductoRequest request);

    ProductoResponse toResponse(Producto producto);
}
