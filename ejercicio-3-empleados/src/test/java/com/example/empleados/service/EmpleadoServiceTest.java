package com.example.empleados.service;

import com.example.empleados.dto.EmpleadoRequest;
import com.example.empleados.exception.EmpleadoNotFoundException;
import com.example.empleados.model.Empleado;
import com.example.empleados.repository.EmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmpleadoServiceTest {

    private EmpleadoService service;

    @BeforeEach
    void setUp() {
        service = new EmpleadoService(new EmpleadoRepository());
    }

    @Test
    void crearEmpleado_debeAsignarIdYGuardarDatos() {
        EmpleadoRequest request = new EmpleadoRequest(
                "Ana", "Lopez", "ana.lopez@correo.com", "Matematicas", "ACTIVO");

        Empleado creado = service.crear(request);

        assertNotNull(creado.getId());
        assertEquals("Ana", creado.getNombre());
        assertEquals("Lopez", creado.getApellido());
        assertEquals("ana.lopez@correo.com", creado.getCorreo());
        assertEquals("Matematicas", creado.getEspecialidad());
        assertEquals("ACTIVO", creado.getEstado());
    }

    @Test
    void obtenerEmpleadoExistente_debeRetornarElEmpleado() {
        Empleado creado = service.crear(new EmpleadoRequest(
                "Carlos", "Perez", "carlos@correo.com", "Fisica", "ACTIVO"));

        Empleado encontrado = service.obtenerPorId(creado.getId());

        assertEquals(creado.getId(), encontrado.getId());
        assertEquals("Carlos", encontrado.getNombre());
    }

    @Test
    void obtenerEmpleadoInexistente_debeLanzarNotFound() {
        assertThrows(EmpleadoNotFoundException.class, () -> service.obtenerPorId(999L));
    }
}
