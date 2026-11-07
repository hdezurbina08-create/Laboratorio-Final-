package com.example.proveedores.service;

import com.example.proveedores.dto.ProveedorRequest;
import com.example.proveedores.exception.ProveedorNotFoundException;
import com.example.proveedores.model.Proveedor;
import com.example.proveedores.repository.ProveedorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProveedorServiceTest {

    private ProveedorService service;

    @BeforeEach
    void setUp() {
        service = new ProveedorService(new ProveedorRepository());
    }

    @Test
    void crearProveedor_debeAsignarIdYGuardarDatos() {
        ProveedorRequest request = new ProveedorRequest(
                "Ana", "Lopez", "ana.lopez@correo.com", "Tecnologia", "ACTIVO");

        Proveedor creado = service.crear(request);

        assertNotNull(creado.getId());
        assertEquals("Ana", creado.getNombre());
        assertEquals("Lopez", creado.getApellido());
        assertEquals("ana.lopez@correo.com", creado.getCorreo());
        assertEquals("Tecnologia", creado.getCategoria());
        assertEquals("ACTIVO", creado.getEstado());
    }

    @Test
    void obtenerProveedorExistente_debeRetornarElProveedor() {
        Proveedor creado = service.crear(new ProveedorRequest(
                "Carlos", "Perez", "carlos@correo.com", "Alimentos", "ACTIVO"));

        Proveedor encontrado = service.obtenerPorId(creado.getId());

        assertEquals(creado.getId(), encontrado.getId());
        assertEquals("Carlos", encontrado.getNombre());
    }

    @Test
    void obtenerProveedorInexistente_debeLanzarNotFound() {
        assertThrows(ProveedorNotFoundException.class, () -> service.obtenerPorId(999L));
    }
}
