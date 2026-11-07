package com.example.profesores.controller;

import com.example.profesores.dto.ProfesorRequest;
import com.example.profesores.model.Profesor;
import com.example.profesores.service.ProfesorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    private final ProfesorService service;

    public ProfesorController(ProfesorService service) {
        this.service = service;
    }

    // POST /api/profesores -> 201 CREATED
    @PostMapping
    public ResponseEntity<Profesor> crear(@Valid @RequestBody ProfesorRequest request) {
        Profesor creado = service.crear(request);
        URI location = URI.create("/api/profesores/" + creado.getId());
        return ResponseEntity.created(location).body(creado);
    }

    // GET /api/profesores  o  GET /api/profesores?especialidad=Matematicas -> 200 OK
    @GetMapping
    public ResponseEntity<List<Profesor>> listar(@RequestParam(required = false) String especialidad) {
        return ResponseEntity.ok(service.listar(especialidad));
    }

    // GET /api/profesores/{id} -> 200 OK | 404 NOT FOUND
    @GetMapping("/{id}")
    public ResponseEntity<Profesor> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.obtenerPorId(id));
    }
}
