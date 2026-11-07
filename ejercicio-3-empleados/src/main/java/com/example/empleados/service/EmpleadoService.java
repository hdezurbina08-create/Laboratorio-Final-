package com.example.empleados.service;

import com.example.empleados.dto.EmpleadoRequest;
import com.example.empleados.exception.EmpleadoNotFoundException;
import com.example.empleados.model.Empleado;
import com.example.empleados.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

    private static final String ESTADO_POR_DEFECTO = "ACTIVO";

    private final EmpleadoRepository repository;

    public EmpleadoService(EmpleadoRepository repository) {
        this.repository = repository;
    }

    public Empleado crear(EmpleadoRequest request) {
        Empleado empleado = new Empleado();
        empleado.setNombre(request.getNombre().trim());
        empleado.setApellido(request.getApellido().trim());
        empleado.setCorreo(request.getCorreo().trim());
        empleado.setEspecialidad(request.getEspecialidad().trim());
        empleado.setEstado(request.getEstado() == null || request.getEstado().isBlank()
                ? ESTADO_POR_DEFECTO
                : request.getEstado().trim());
        return repository.save(empleado);
    }

    public List<Empleado> listar(String especialidad) {
        if (especialidad == null || especialidad.isBlank()) {
            return repository.findAll();
        }
        return repository.findByEspecialidad(especialidad.trim());
    }

    public Empleado obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmpleadoNotFoundException(id));
    }
}
