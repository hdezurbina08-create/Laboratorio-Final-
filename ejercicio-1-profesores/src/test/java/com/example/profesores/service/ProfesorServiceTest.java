package com.example.profesores.service;

import com.example.profesores.dto.ProfesorRequest;
import com.example.profesores.exception.ProfesorNotFoundException;
import com.example.profesores.model.Profesor;
import com.example.profesores.repository.ProfesorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfesorServiceTest {

    private ProfesorService service;

    @BeforeEach
    void setUp() {
        service = new ProfesorService(new ProfesorRepository());
    }

    @Test
    void crearProfesor_debeAsignarIdYGuardarDatos() {
        ProfesorRequest request = new ProfesorRequest(
                "Ana", "Lopez", "ana.lopez@correo.com", "Matematicas", "ACTIVO");

        Profesor creado = service.crear(request);

        assertNotNull(creado.getId());
        assertEquals("Ana", creado.getNombre());
        assertEquals("Lopez", creado.getApellido());
        assertEquals("ana.lopez@correo.com", creado.getCorreo());
        assertEquals("Matematicas", creado.getEspecialidad());
        assertEquals("ACTIVO", creado.getEstado());
    }

    @Test
    void obtenerProfesorExistente_debeRetornarElProfesor() {
        Profesor creado = service.crear(new ProfesorRequest(
                "Carlos", "Perez", "carlos@correo.com", "Fisica", "ACTIVO"));

        Profesor encontrado = service.obtenerPorId(creado.getId());

        assertEquals(creado.getId(), encontrado.getId());
        assertEquals("Carlos", encontrado.getNombre());
    }

    @Test
    void obtenerProfesorInexistente_debeLanzarNotFound() {
        assertThrows(ProfesorNotFoundException.class, () -> service.obtenerPorId(999L));
    }
}
