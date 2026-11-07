package com.example.profesores.service;

import com.example.profesores.dto.ProfesorRequest;
import com.example.profesores.exception.ProfesorNotFoundException;
import com.example.profesores.model.Profesor;
import com.example.profesores.repository.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorService {

    private static final String ESTADO_POR_DEFECTO = "ACTIVO";

    private final ProfesorRepository repository;

    public ProfesorService(ProfesorRepository repository) {
        this.repository = repository;
    }

    public Profesor crear(ProfesorRequest request) {
        Profesor profesor = new Profesor();
        profesor.setNombre(request.getNombre().trim());
        profesor.setApellido(request.getApellido().trim());
        profesor.setCorreo(request.getCorreo().trim());
        profesor.setEspecialidad(request.getEspecialidad().trim());
        profesor.setEstado(request.getEstado() == null || request.getEstado().isBlank()
                ? ESTADO_POR_DEFECTO
                : request.getEstado().trim());
        return repository.save(profesor);
    }

    public List<Profesor> listar(String especialidad) {
        if (especialidad == null || especialidad.isBlank()) {
            return repository.findAll();
        }
        return repository.findByEspecialidad(especialidad.trim());
    }

    public Profesor obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProfesorNotFoundException(id));
    }
}
