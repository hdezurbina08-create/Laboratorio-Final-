package com.example.empleados.controller;

import com.example.empleados.dto.EmpleadoRequest;
import com.example.empleados.model.Empleado;
import com.example.empleados.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final EmpleadoService service;

    public EmpleadoController(EmpleadoService service) {
        this.service = service;
    }

    // POST /api/empleados -> 201 CREATED
    @PostMapping
    public ResponseEntity<Empleado> crear(@Valid @RequestBody EmpleadoRequest request) {
        Empleado creado = service.crear(request);
        URI location = URI.create("/api/empleados/" + creado.getId());
        return ResponseEntity.created(location).body(creado);
    }

    // GET /api/empleados  o  GET /api/empleados?especialidad=Matematicas -> 200 OK
    @GetMapping
    public ResponseEntity<List<Empleado>> listar(@RequestParam(required = false) String especialidad) {
        return ResponseEntity.ok(service.listar(especialidad));
    }

    // GET /api/empleados/{id} -> 200 OK | 404 NOT FOUND
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.obtenerPorId(id));
    }
}
