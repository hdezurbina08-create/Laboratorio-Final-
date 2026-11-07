package com.example.proveedores.controller;

import com.example.proveedores.dto.ProveedorRequest;
import com.example.proveedores.model.Proveedor;
import com.example.proveedores.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    private final ProveedorService service;

    public ProveedorController(ProveedorService service) {
        this.service = service;
    }

    // POST /api/proveedores -> 201 CREATED
    @PostMapping
    public ResponseEntity<Proveedor> crear(@Valid @RequestBody ProveedorRequest request) {
        Proveedor creado = service.crear(request);
        URI location = URI.create("/api/proveedores/" + creado.getId());
        return ResponseEntity.created(location).body(creado);
    }

    // GET /api/proveedores  o  GET /api/proveedores?categoria=Tecnologia -> 200 OK
    @GetMapping
    public ResponseEntity<List<Proveedor>> listar(@RequestParam(required = false) String categoria) {
        return ResponseEntity.ok(service.listar(categoria));
    }

    // GET /api/proveedores/{id} -> 200 OK | 404 NOT FOUND
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.obtenerPorId(id));
    }
}
