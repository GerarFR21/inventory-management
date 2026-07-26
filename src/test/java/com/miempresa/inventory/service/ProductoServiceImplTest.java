package com.miempresa.inventory.service;

import com.miempresa.inventory.dto.ProductoRequest;
import com.miempresa.inventory.dto.ProductoResponse;
import com.miempresa.inventory.entity.Producto;
import com.miempresa.inventory.mapper.ProductoMapper;
import com.miempresa.inventory.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceImplTest {

    @Mock
    private ProductoRepository repository;

    @Mock
    private ProductoMapper mapper;

    @InjectMocks
    private ProductoServiceImpl service;

    @Test
    void shouldReturnAllProducts(){

        // Given
        List<Producto> productos = List.of(
                new Producto(1L, "Keyboard", BigDecimal.valueOf(29.99)),
                new Producto(2L, "Mouse", BigDecimal.valueOf(7.99))
        );

        when(repository.findAll())
                .thenReturn(productos);

        when(mapper.toResponse(productos.get(0)))
                .thenReturn(new ProductoResponse(
                        1L, "Keyboard", BigDecimal.valueOf(29.99)));

        when(mapper.toResponse(productos.get(1)))
                .thenReturn(new ProductoResponse(
                        2L, "Mouse", BigDecimal.valueOf(7.99)));


        // When
        List<ProductoResponse> result = service.findAll();


        //Then
        assertEquals(2, result.size());

        ProductoResponse first = result.getFirst();
        ProductoResponse second = result.getLast();

        assertEquals(1L, first.id());
        assertEquals("Keyboard", first.nombre());
        assertEquals(BigDecimal.valueOf(29.99), first.precio());

        assertEquals(2L, second.id());
        assertEquals("Mouse", second.nombre());
        assertEquals(BigDecimal.valueOf(7.99), second.precio());

        verify(repository).findAll();

        verify(mapper, times(2))
                .toResponse(any(Producto.class));

    }

    @Test
    void shouldReturnProductById(){

        // Given
        Long id = 1L;

        Producto foundProduct =  new Producto(
                1L,
                "Keyboard",
                BigDecimal.valueOf(29.99)
        );

        ProductoResponse mappedProducto = new ProductoResponse(
                1L,
                "Keyboard",
                BigDecimal.valueOf(29.99)
        );

        when(repository.findById(id))
                .thenReturn(Optional.of(foundProduct));

        when(mapper.toResponse(foundProduct))
                .thenReturn(mappedProducto);

        // When
        ProductoResponse result = service.findById(id);

        //Then
        assertEquals(1L, result.id());
        assertEquals("Keyboard", result.nombre());
        assertEquals(BigDecimal.valueOf(29.99), result.precio());

        verify(repository).findById(id);
        verify(mapper).toResponse(foundProduct);
    }

    @Test
    void shouldSaveProduct(){

        //Given
        ProductoRequest request = new ProductoRequest(
                "Keyboard",
                BigDecimal.valueOf(29.99));

        Producto createdProducto = new Producto(
                1L,
                "Keyboard",
                BigDecimal.valueOf(29.99)
        );

        Producto productoWithoutId = new Producto(
                null,
                "Keyboard",
                BigDecimal.valueOf(29.99));

        ProductoResponse mappedProducto = new ProductoResponse(
                1L,
                "Keyboard",
                BigDecimal.valueOf(29.99)
        );

        when(repository.save(productoWithoutId))
                .thenReturn(createdProducto);

        when(mapper.toEntity(request))
                .thenReturn(productoWithoutId);

        when(mapper.toResponse(createdProducto))
                .thenReturn(mappedProducto);

        //When
        ProductoResponse result = service.create(request);

        //Then
        assertEquals(1L, result.id());
        assertEquals("Keyboard", result.nombre());
        assertEquals(BigDecimal.valueOf(29.99), result.precio());

        verify(repository).save(productoWithoutId);
        verify(mapper).toEntity(request);
        verify(mapper).toResponse(createdProducto);
    }

    @Test
    void shouldUpdateProduct(){

        //Given
        Long id = 1L;

        ProductoRequest request = new ProductoRequest(
                "Updated Keyboard",
                BigDecimal.valueOf(24.99));


        Producto foundProducto = new Producto(
                1L,
                "Keyboard",
                BigDecimal.valueOf(29.99)
        );

        Producto updatedProducto = new Producto(
                1L,
                "Updated Keyboard",
                BigDecimal.valueOf(24.99)
        );

        ProductoResponse response = new ProductoResponse(
                1L,
                "Updated Keyboard",
                BigDecimal.valueOf(24.99)
        );

        when(repository.findById(id))
                .thenReturn(Optional.of(foundProducto));

        when(repository.save(any(Producto.class)))
                .thenReturn(updatedProducto);

        when(mapper.toResponse(updatedProducto))
                .thenReturn(response);

        //When
        ProductoResponse result = service.update(id, request);

        //Then
        assertEquals("Updated Keyboard", foundProducto.getNombre());
        assertEquals(BigDecimal.valueOf(24.99), foundProducto.getPrecio());

        assertEquals(1L, result.id());
        assertEquals("Updated Keyboard", result.nombre());
        assertEquals(BigDecimal.valueOf(24.99), result.precio());

        verify(repository).findById(id);
        verify(repository).save(any(Producto.class));
        verify(mapper).toResponse(updatedProducto);
    }

    @Test
    void shouldDeleteProduct(){

        //Given
        Long id = 1L;

        //When
        service.delete(id);

        //Then
        verify(repository).deleteById(id);

    }
}
