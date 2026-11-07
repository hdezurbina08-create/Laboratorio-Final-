package com.example.proveedores.service;

import com.example.proveedores.dto.ProveedorRequest;
import com.example.proveedores.exception.ProveedorNotFoundException;
import com.example.proveedores.model.Proveedor;
import com.example.proveedores.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {

    private static final String ESTADO_POR_DEFECTO = "ACTIVO";

    private final ProveedorRepository repository;

    public ProveedorService(ProveedorRepository repository) {
        this.repository = repository;
    }

    public Proveedor crear(ProveedorRequest request) {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(request.getNombre().trim());
        proveedor.setApellido(request.getApellido().trim());
        proveedor.setCorreo(request.getCorreo().trim());
        proveedor.setCategoria(request.getCategoria().trim());
        proveedor.setEstado(request.getEstado() == null || request.getEstado().isBlank()
                ? ESTADO_POR_DEFECTO
                : request.getEstado().trim());
        return repository.save(proveedor);
    }

    public List<Proveedor> listar(String categoria) {
        if (categoria == null || categoria.isBlank()) {
            return repository.findAll();
        }
        return repository.findByCategoria(categoria.trim());
    }

    public Proveedor obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProveedorNotFoundException(id));
    }
}
